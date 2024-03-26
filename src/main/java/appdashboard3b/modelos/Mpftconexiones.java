package appdashboard3b.modelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.beans.PftConexiones;
import appdashboard3b.consultas.Cconexiones;
import appdashboard3b.interfaces.Ipftconexiones;

@Service
public class Mpftconexiones implements Ipftconexiones{
	
	@Autowired
    private JdbcTemplate jdbcT;
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public PftConexiones pftConexiones(String region) {
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Cconexiones.PFT_CONEXIONES.toString(), region);	
			if(rs.next()) {
				return new PftConexiones(rs.getString("tclave"), rs.getString("host"), rs.getString("puerto"), rs.getString("servicio"), rs.getString("base"), rs.getString("driver"), rs.getString("url"));
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return new PftConexiones();
	}

}
