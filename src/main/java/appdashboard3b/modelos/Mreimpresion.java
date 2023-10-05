package appdashboard3b.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.beans.Facturas;
import appdashboard3b.beans.Rreimpresion;
import appdashboard3b.consultas.Creimpresion;
import appdashboard3b.interfaces.Ireimpresion;
import appdashboard3b.mapeadores.FacturaRowMapper;
import appdashboard3b.utelerias.RT;

@Service
public class Mreimpresion implements Ireimpresion{
	@Autowired
    private JdbcTemplate jdbcT;

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Facturas> reimpresion(Rreimpresion rreimpresion) {		
		try {
			if(rreimpresion.getTipo() == 1) {					
				int subTipo = (rreimpresion.getSerie() == null || rreimpresion.getSerie().isEmpty())?1:2;
				
				return jdbcT.query(
				(subTipo == 1)?
						Creimpresion.GETFACFOLIO.toString()
					:
						Creimpresion.GETFACFOLIOSERIE.toString(),
				new FacturaRowMapper(),
				new Object[] { 
					(subTipo == 1)?
						rreimpresion.getFolio()
					:
						rreimpresion.getSerie(),rreimpresion.getFolio()			
				});	
			}else if(rreimpresion.getTipo() == 2) {
				return jdbcT.query(Creimpresion.GETFACRFC.toString(),new FacturaRowMapper(),new Object[] { rreimpresion.getRfc(), RT.dateToStringYMD(RT.restaDias(new Date(), 30)), RT.dateToStringYMD(new Date()) });	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Facturas>();
	}
}
