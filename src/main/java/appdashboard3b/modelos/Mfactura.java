package appdashboard3b.modelos;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.Fclientes;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.PftConexiones;
import appdashboard3b.beans.Ticket;
import appdashboard3b.beans.TicketDetalle;
import appdashboard3b.consultas.Cconexiones;
import appdashboard3b.consultas.Cfactura;
import appdashboard3b.interfaces.Ifactura;

@Service
public class Mfactura implements Ifactura{
	
	@Autowired
    private JdbcTemplate jdbcT;
	
	@Autowired
	Mfclientes fc;
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public ExisteFactura existeFactura(Ticket ticket) {
		try {		
			SqlRowSet rs = jdbcT.queryForRowSet(Cfactura.Facturas.toString(), ticket.getRegion(), ticket.getTienda(), ticket.getTicket(), ticket.getCaja(), ticket.getFechaCompra());	
			if(rs.next()) {	
				ticket.setFolio("F: "+rs.getString("folio_factura"));
				return new ExisteFactura(1, ticket);
			}else {
				rs = jdbcT.queryForRowSet(Cfactura.Solicitud.toString(), ticket.getTienda(), ticket.getCaja(), ticket.getTicket(), ticket.getFechaCompra());	
				if(rs.next()) {
					boolean tipo = rs.getString("tipo").equals("VENTA CERO");
					ticket.setFolio((tipo)?"VS: "+rs.getString("folio_sol"):"S: "+rs.getString("folio_sol"));
					return (tipo)?new ExisteFactura(3, ticket):new ExisteFactura(2, ticket);
				}else {
					if(ticket.getRegion() != null) {
						if(!ticket.getRegion().trim().equals("")) {
							try {
								rs = jdbcT.queryForRowSet(Cconexiones.PFT_CONEXIONES.toString(), ticket.getRegion());	
								if(rs.next()) {
									ticket.setConexion(new PftConexiones(rs.getString("tclave"), rs.getString("host"), rs.getString("puerto"), rs.getString("servicio"), rs.getString("base"), rs.getString("driver"), rs.getString("url")));
									if(ticket.getConexion() != null) {
										return new ExisteFactura(0, ticket);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								return new ExisteFactura(-2, null);	
							}
						}
					}					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();					
		}
		return new ExisteFactura(-1, null);	
	}	
	
	public boolean guardarFactura(GenerarFactura genera) {
		if(genera.getTickets().size() > 0){
			Fclientes fclientes = genera.getFclientes();
			if(fclientes != null){
				try {
					for (Ticket ticket : genera.getTickets()) {
						if(ticket.getFolio() != null && !ticket.getFolio().equals("")){	
							KeyHolder facturakey = new GeneratedKeyHolder();
							jdbcT.update(connection -> {
								 PreparedStatement ps = connection.prepareStatement(Cfactura.IFacturas.toString(), new String[] { "id_factura" });
								 ps.setString(1,	ticket.getFolio());					 
								 ps.setString(2,  	ticket.getTicket());
								 ps.setString(3, 	ticket.getTienda()+"");
								 ps.setString(4, 	ticket.getRegion());
								 ps.setString(5, 	ticket.getCaja());
								 ps.setString(6, 	ticket.getFechaCompra());
								 ps.setString(7, 	ticket.getUuid());
								 ps.setString(8, 	ticket.getXml());
								 ps.setString(9, 	ticket.getTncrvendflag());
								 ps.setString(10, 	fclientes.getRfc());
								 ps.setString(11,   fclientes.getCorreo());
								 ps.setDouble(12,   ticket.getTotal());
								 ps.setDouble(13,   ticket.getSubtotal());
								 ps.setDouble(14,   ticket.getIva());
								 ps.setDouble(15,   (ticket.getIeps()== null)?0.0:ticket.getIeps());
								 ps.setString(16,	ticket.getPdf());		
								 return ps;
					        },facturakey);							
							Long id_factura= (long) facturakey.getKey().longValue();
							
							if(id_factura > 0) {
								for (TicketDetalle detalle : ticket.getDetalles()) {
									KeyHolder facturaDkey = new GeneratedKeyHolder();
									jdbcT.update(connection -> {
										 PreparedStatement ps = connection.prepareStatement(Cfactura.IFacturasD.toString(), new String[] { "id_fac_det" });
										 ps.setString(1,	ticket.getFolio());		
										 ps.setString(2, 	ticket.getTncrvendflag());
										 ps.setString(3, 	ticket.getTienda()+"");
										 ps.setString(4, 	ticket.getRegion());
										 ps.setString(5, 	detalle.getIclave());
										 ps.setString(6, 	detalle.getCClaveProdServ());
										 ps.setDouble(7,    detalle.getValUnit());
										 ps.setDouble(8,    detalle.getAtmacant());
										 ps.setDouble(9,    detalle.getSubtotal());
										 ps.setDouble(10,    detalle.getIepsPrc());
										 ps.setDouble(11,   detalle.getIepsMont());
										 ps.setDouble(12,   detalle.getIvaPrc());
										 ps.setDouble(13,   detalle.getIvaMont());
										 return ps;
							        },facturaDkey);	
									
									Long id_fac_det= (long) facturaDkey.getKey().longValue();
									jdbcT.update(Cfactura.IFacturasT.toString() ,id_factura, id_fac_det, detalle.getIdesc());
								}					
							}					   			
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				try {
					if(fc.cliente(fclientes.getRfc()) != null){
						fc.actualizar(fclientes);
					}else {
						fc.insertar(fclientes);
					}				
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}					
		}			
		return false;
	}
	

}
