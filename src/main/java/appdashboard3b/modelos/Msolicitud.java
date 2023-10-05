package appdashboard3b.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import appdashboard3b.beans.Solicitudes;
import appdashboard3b.consultas.Cfactura;
import appdashboard3b.interfaces.Isolicitud;

@Service
public class Msolicitud implements Isolicitud{
	
	@Autowired
    private JdbcTemplate jdbcT;
	
	public Integer getFolioSol() {
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cfactura.FolioSol.toString());	
			if(rs.next()) {	
				Integer folio =rs.getInt("folio");
				if(folio > 0) {
					jdbcT.update(Cfactura.FolioSolU.toString());
					return folio;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean guardarSol(List<Solicitudes> solicitudes) {
		try {
			Integer foilo = getFolioSol();
			if(foilo != null && foilo > 0) {
				for (Solicitudes solicitud : solicitudes) {
					String ticket = solicitud.getNum_ticket();			
					int i = ticket.contains(".") ? ticket.lastIndexOf(".") : ticket.length();			
					ticket = ticket.substring(0,i);
					jdbcT.update(Cfactura.ISolicitud.toString(), solicitud.getStatus_sol(), solicitud.getRfc(), solicitud.getTclave(), solicitud.getFecha_compra(), solicitud.getNum_caja(), ticket, solicitud.getDescripcion(), solicitud.getNum_factura(), foilo, solicitud.getTipo(), solicitud.getSucursal());
				}	
				return true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
