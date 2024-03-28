package appdashboard3b.modelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import appdashboard3b.beans.Peticiones;
import appdashboard3b.beans.Ticket;
import appdashboard3b.consultas.Cpeticiones;
import appdashboard3b.interfaces.Ipeticiones;




@Service
public class Mpeticiones implements Ipeticiones{
	@Autowired
    private JdbcTemplate jdbcT;

	@Override
	
	public Peticiones existePeticion(Ticket ticket) {
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cpeticiones.EXISTE.toString(), ticket.getTicket(), ticket.getTienda(), ticket.getCaja(), ticket.getFechaCompra());	
			if(rs.next()) {	
				return new Peticiones(rs.getInt("id"), ticket.getTicket(), ticket.getTienda()+"", ticket.getCaja(), ticket.getFechaCompra(), rs.getString("total"), rs.getString("rfc"), rs.getString("nombre"), rs.getString("email"), rs.getString("cp"), rs.getString("email2"), rs.getString("uso_cfdi"), rs.getString("regimen_fiscal"), rs.getString("estatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean inserta(Ticket ticket) {
		try {
			jdbcT.update(Cpeticiones.INSERTA.toString(), ticket.getTicket(), ticket.getTienda(), ticket.getCaja(), ticket.getFechaCompra(), ticket.getTotal(), ticket.getFclientes().getRfc(), ticket.getFclientes().getRazonSocial(), ticket.getFclientes().getCorreo(), ticket.getFclientes().getDomicilio().getCp(), (ticket.getFclientes().getCorreo2() != null)?ticket.getFclientes().getCorreo2():"", ticket.getFclientes().getUsoCFDI(), ticket.getFclientes().getRegimenFiscal());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override
	public boolean actualiza(Ticket ticket, String status) {
		try {
			jdbcT.update(Cpeticiones.ACTUALIZA.toString(), status, ticket.getTicket(), ticket.getTienda(), ticket.getCaja(), ticket.getFechaCompra());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
