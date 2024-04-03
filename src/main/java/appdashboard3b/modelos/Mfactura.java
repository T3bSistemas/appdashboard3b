package appdashboard3b.modelos;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.beans.DetalleTickets;
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
	private static final Logger logger = LoggerFactory.getLogger(Mfactura.class);
	
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
				ticket.setFolio("F: "+rs.getString("folio_factura")+ " "+rs.getString("serie"));
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
								logger.error("existeFactura PFT_CONEXIONES- "+e.getMessage());
								return new ExisteFactura(-2, null);	
							}
						}
					}					
				}
			}
		} catch (Exception e) {
			logger.error("existeFactura(1)- "+e.getMessage());				
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
					logger.error("guardarFactura- "+e.getMessage());
				}	
				
				try {
					if(fc.cliente(fclientes.getRfc()) != null){
						fc.actualizar(fclientes);
					}else {
						fc.insertar(fclientes);
					}				
				} catch (Exception e) {
					logger.error("guardarFactura Cliente- "+e.getMessage());
				}
				return true;
			}					
		}			
		return false;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String regionTienda(String fecha, Integer tienda) {
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cfactura.Region.toString(),fecha, tienda);	
			if(rs.next()) {	
				return rs.getString("talmacen");
			}else {
				SqlRowSet rs2 = jdbcT.queryForRowSet(Cfactura.RegionAnt.toString(), tienda);	
				if(rs2.next()) {	
					return rs2.getString("talmacen");
				}				
			}
		} catch (Exception e) {
			logger.error("regionTienda- "+e.getMessage());
		}
		return "";
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public DetalleTickets getTicketsDetalles(List<Ticket> tickets) {
		Map<String, String> formasdepago = formasdepago();
		try {
			if(formasdepago.size() > 0) {
				for (Ticket ticket : tickets) {
					int tipoPago = (ticket.getTipoPago() != null && !ticket.getTipoPago().equals(""))?Integer.parseInt(ticket.getTipoPago()):0;
					String 	claveSAT = formasdepago.containsKey(tipoPago+"")?formasdepago.get(tipoPago+""):"" ;
					if(!claveSAT.equals("")) {
						claveSAT = (claveSAT.length() == 1)?"0"+claveSAT:claveSAT;
						SqlRowSet rs = jdbcT.queryForRowSet(Cfactura.Serie.toString(), ticket.getTienda());
						if(rs.next()) {
							String tdir			=	rs.getString("tdir");
							       tdir         =  (tdir!=null)?tdir.trim():tdir;
							String tncrvendflag	=	rs.getString("tncrvendflag");
								   tncrvendflag =  (tncrvendflag!=null)?tncrvendflag.trim():tncrvendflag;
							String temail		=	rs.getString("temail");
								   temail       =  (temail!=null)?temail.trim():temail;
							if(!isNull(tdir) && !isNull(tncrvendflag)) {
								ticket.setTicketAdi(claveSAT, tdir, tncrvendflag, temail);
								List<TicketDetalle> Listdetalle = new ArrayList<TicketDetalle>();;
								if(ticket.getDetalles().size() > 0) {
									for (TicketDetalle detalle : ticket.getDetalles()) {
										if( detalle.getAtmacant() > 0 && detalle.getAtmventa()> 0d) {	
											SqlRowSet rs2 = jdbcT.queryForRowSet(Cfactura.IvaiEps.toString(), detalle.getIclave());
											if(rs2.next()) {
												String CClaveUnidad 	= rs2.getString("c_ClaveUnidad");
													   CClaveUnidad     = (CClaveUnidad!=null)?CClaveUnidad.trim():CClaveUnidad;	
												String CClaveProdServ	= rs2.getString("c_ClaveProdServ");
													   CClaveProdServ     = (CClaveProdServ!=null)?CClaveProdServ.trim():CClaveProdServ;
												if(detalle.getAtmdesc() != null && detalle.getAtmdesc().equals("PS")) {
														continue;														
												}else {
													Listdetalle.add(new TicketDetalle(detalle.getIclave(), detalle.getAtmacant(), detalle.getAtmventa(), detalle.getIvClave(), detalle.getIeClave(), rs2.getString("idesc"), rs2.getString("iunidad"), CClaveUnidad.equals("") ?"H87":CClaveUnidad , CClaveProdServ.equals("") ?"01010101":CClaveProdServ, rs2.getString("iv_factor"), rs2.getString("ie_factor"), detalle.getAtmventa()*rs2.getDouble("iv_factor"), detalle.getAtmventa()*rs2.getDouble("ie_factor"), detalle.getAtmdesc(), detalle.getGclave(), detalle.getLclave()));
												}														
											}
										}
									}
								}
								
								if(Listdetalle != null && Listdetalle.size() > 0) {
									ticket.setDetalle(Listdetalle);										
								}else {
									return new DetalleTickets(5, "Problemas de datos, Detalle de ticket IVA IEPS", null);
								}
							}else {
								return new DetalleTickets(4, "Problemas de datos, detalle tienda CP y Serie", null);
							}
						}else {
							return new DetalleTickets(4, "Problemas de datos, detalle ticket Serie", null);
						}
					}else {
						return new DetalleTickets(3, "Problemas de datos, detalle ticket formasdepago no existe", null);
					}					 				
				}
			}else {
				return new DetalleTickets(2, "Problemas de datos, detalle ticket formasdepago", null);
			}
			
		} catch (Exception e) {
			logger.error("getTicketsDetalles- "+e.getMessage());
			return new DetalleTickets(1, "Problemas de datos", null);
		}
		return new DetalleTickets(0, "", tickets);
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Map<String, String> formasdepago() {
		Map<String, String> formasdepago = new HashMap<String, String>();
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cfactura.FormasdePago.toString());
			while(rs.next()) {
				formasdepago.put(rs.getString("fp_clave").trim(), rs.getString("clave_sat").trim());
			}
		} catch (Exception e) {
			logger.error("formasdepago- "+e.getMessage());
		}
		return formasdepago;
	}
	
	public boolean isNull(String data) {
		try {
			if(data != null) {
				if(!data.trim().equals("")) {
					return false;
				}
			}
		} catch (Exception e) {
			logger.error("isNull- "+e.getMessage());
		}
		return true;
	}

}
