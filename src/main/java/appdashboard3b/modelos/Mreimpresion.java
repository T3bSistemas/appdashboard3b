package appdashboard3b.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
	private static final Logger logger = LoggerFactory.getLogger(Mreimpresion.class);
	
	@Autowired
    private JdbcTemplate jdbcT;
	
	@Autowired
	Mcorreo co;

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
			logger.error("reimpresion- "+e.getMessage());
		}
		return new ArrayList<Facturas>();
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public boolean correo(String uuid, String to, String toReply) {
		try {			
			SqlRowSet rs =  jdbcT.queryForRowSet(Creimpresion.GETFACUUID.toString(),uuid);	
			if(rs.next()) {
				Address[] destinos =  null;
				if(toReply.isEmpty()) {
					destinos = new Address[1];
					destinos[0] = new InternetAddress(to); 
				}else {
					destinos = new Address[2];
					destinos[0] = new InternetAddress(to); 
					destinos[1] = new InternetAddress(toReply); 
				}	 		
		 		
				String pdfBase64 	= rs.getString("pdfBase64");
				String xmlbase 		= rs.getString("xml");
				
				if((pdfBase64 != null && !pdfBase64.equals("")) && (xmlbase != null && !xmlbase.equals(""))) {
					String mensaje_mail = "Por medio de la presente le informamos que TIENDAS TRES B, SA DE CV\n" + 
			    			"le ha enviado un nuevo Comprobante Fiscal Digital. Este comprobante esta disponible\n" + 
			    			"como archivo adjunto a este correo.";
					return co.EnviarMail("3befact-noreply@t3b.com.mx", destinos, mensaje_mail, "Reenvío de Factura Tiendas 3B",uuid,  xmlbase, pdfBase64);
					
				}				
			}		 
		} catch (Exception e) {
			logger.error("correo- "+e.getMessage());
		}
		return false;
	}
}
