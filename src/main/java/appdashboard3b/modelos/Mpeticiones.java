package appdashboard3b.modelos;

import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import appdashboard3b.beans.Peticiones;
import appdashboard3b.beans.Ticket;
import appdashboard3b.consultas.Cpeticiones;
import appdashboard3b.interfaces.Ipeticiones;




@Service
public class Mpeticiones implements Ipeticiones{
	private static final Logger logger = LoggerFactory.getLogger(Mpeticiones.class);
	
	@Autowired
    private JdbcTemplate jdbcT;

	@Override
	
	public Peticiones existePeticion(Ticket ticket) {
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cpeticiones.EXISTE.toString(), ticket.getTicket(), ticket.getTienda(), ticket.getCaja(), ticket.getFechaCompra());	
			if(rs.next()) {	
				return new Peticiones(rs.getInt("id"), ticket.getTicket(), ticket.getTienda()+"", ticket.getCaja(), ticket.getFechaCompra(), rs.getString("total"), rs.getString("rfc"), rs.getString("nombre"), rs.getString("email"), rs.getString("cp"), rs.getString("email2"), rs.getString("uso_cfdi"), rs.getString("regimen_fiscal"), rs.getString("fecha_peticion"), rs.getString("estatus"));
			}
		} catch (Exception e) {
			logger.error("existePeticion- "+e.getMessage());
		}
		return null;
	}

	@Override
	public Long inserta(Ticket ticket, String estatus) {
		try {
			KeyHolder peticionkey = new GeneratedKeyHolder();
			jdbcT.update(connection -> {
				 PreparedStatement ps = connection.prepareStatement(Cpeticiones.INSERTA.toString(), new String[] { "id" });
				 ps.setString(1,	ticket.getTicket());					 
				 ps.setInt(   2,  	ticket.getTienda());
				 ps.setString(3, 	ticket.getCaja());
				 ps.setString(4, 	ticket.getFechaCompra());
				 ps.setDouble(5, 	ticket.getTotal());
				 ps.setString(6, 	ticket.getFclientes().getRfc());
				 ps.setString(7, 	ticket.getFclientes().getRazonSocial());	
				 ps.setString(8, 	ticket.getFclientes().getCorreo());
				 ps.setString(9, 	ticket.getFclientes().getDomicilio().getCp());
				 ps.setString(10, 	(ticket.getFclientes().getCorreo2() != null)?ticket.getFclientes().getCorreo2():"");
				 ps.setString(11, 	ticket.getFclientes().getUsoCFDI());
				 ps.setString(12, 	ticket.getFclientes().getRegimenFiscal());
				 ps.setString(13, 	estatus);
				 return ps;
	        },peticionkey);							
			return (long) peticionkey.getKey().longValue();
		} catch (Exception e) {
			logger.error("inserta- "+e.getMessage());
		}
		return null;		
	}
	
	@Override
	public boolean actualiza(Ticket ticket, String status) {
		try {
			jdbcT.update(Cpeticiones.ACTUALIZA.toString(), status, ticket.getTicket(), ticket.getTienda(), ticket.getCaja(), ticket.getFechaCompra());
			return true;
		} catch (Exception e) {
			logger.error("actualiza- "+e.getMessage());
		}
		return false;
		
	}
	
	public void ingresaPeticion(Ticket ticket, Ticket ticketR) {
		try {	
			if(ticket.getFclientes() != null) {
				Peticiones pend = existePeticion(ticket);
				if(ticketR != null) {				
					if(
					(ticketR.getTipoPago() == null || ticketR.getTipoPago().equals("")) ||
					(ticketR.getDetalles() == null || ticketR.getDetalles().size() == 0) ||
					(ticketR.getRegion() == null   || ticketR.getRegion().equals("")) 
					){	
						if(pend == null)
							inserta(ticket, "P");
						else
							actualiza(ticket,"P");
					}
				} else {
					if(pend == null) 
						inserta(ticket, "E");
					else
						actualiza(ticket,"E");			
				}	
			}						
		} catch (Exception e) {
			logger.error("ingresaPeticion- "+e.getMessage());
		}
	}

}
