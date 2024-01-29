package appdashboard3b.modelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.beans.Domicilio;
import appdashboard3b.beans.Fclientes;
import appdashboard3b.consultas.Cfclientes;
import appdashboard3b.interfaces.Ifclientes;



@Service
public class Mfclientes implements Ifclientes{
	@Autowired
    private JdbcTemplate jdbcT;
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Fclientes cliente(String rfc) {			
		try {		
			SqlRowSet rs = jdbcT.queryForRowSet(Cfclientes.FClientes.toString(), rfc);	
			if(rs.next()) {
				return new Fclientes(rs.getString("crfc"), rs.getString("cnombre"), rs.getString("cemail"), "facturamicompra@t3b.com.mx", rs.getString("regimen_fiscal"), rs.getString("uso_cfdi") , 
					   new Domicilio(rs.getString("ccalle"), rs.getString("ccolonia"), rs.getString("ccp"), rs.getString("cestado"), rs.getString("cmunicipio"), rs.getString("cnumext"), rs.getString("cnumint"), rs.getString("cpais")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	
	public boolean actualizar(Fclientes fclientes) {
		try {
			jdbcT.update(Cfclientes.ACTUALIZAR.toString(), fclientes.getRfc(), fclientes.getRazonSocial(), fclientes.getCorreo(), fclientes.getCorreo2(), fclientes.getDomicilio().getCalle(), fclientes.getDomicilio().getNumExt(), fclientes.getDomicilio().getNumInt(), fclientes.getDomicilio().getColonia(), fclientes.getDomicilio().getMunAlc(), fclientes.getDomicilio().getEstado(), fclientes.getDomicilio().getPais(), fclientes.getDomicilio().getCp(), false, fclientes.getUsoCFDI(), fclientes.getRegimenFiscal(), fclientes.getRfc());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean insertar(Fclientes fclientes) {
		try {
			jdbcT.update(Cfclientes.INSRTAR.toString(), fclientes.getRfc(), fclientes.getRazonSocial(), fclientes.getCorreo(), fclientes.getCorreo2(), fclientes.getDomicilio().getCalle(), fclientes.getDomicilio().getNumExt(), fclientes.getDomicilio().getNumInt(), fclientes.getDomicilio().getColonia(), fclientes.getDomicilio().getMunAlc(), fclientes.getDomicilio().getEstado(), fclientes.getDomicilio().getPais(), fclientes.getDomicilio().getCp(), false, fclientes.getUsoCFDI(), fclientes.getRegimenFiscal());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
