package appdashboard3b.modelos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import appdashboard3b.interfaces.Icatalogo;
import appdashboard3b.beans.Usoscfdi;
import appdashboard3b.consultas.Ccatalogo;
import appdashboard3b.beans.Regimenesfiscales;
import appdashboard3b.beans.Catalogos;
import appdashboard3b.beans.Questions;

@Service
public class Mcatalogo implements Icatalogo{
	@Autowired
    private JdbcTemplate jdbcT;
	
	@Override
	public Catalogos Catalogos() {
		Catalogos catalogos = new Catalogos();
		try {
			catalogos.setQuestions(Questions());
			catalogos.setUsoscfdi(Usoscfdi());
			catalogos.setRegimenesfiscales(Regimenesfiscales());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catalogos;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Questions> Questions() {
		List<Questions> questions = new ArrayList<Questions>();
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Ccatalogo.QUESTIONS.toString());
			while(rs.next()) {
				questions.add(new Questions(rs.getInt("id"), rs.getString("question"), rs.getString("details")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Usoscfdi> Usoscfdi() {
		List<Usoscfdi> usosCfdi = new ArrayList<Usoscfdi>();
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Ccatalogo.USOS_CFDI.toString());
			while(rs.next()) {
				usosCfdi.add(new Usoscfdi(rs.getString("clave"), rs.getString("descripcion"), rs.getInt("estatus")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usosCfdi;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public List<Regimenesfiscales> Regimenesfiscales() {
		List<Regimenesfiscales> regimenesfiscales = new ArrayList<Regimenesfiscales>();
		try {
			SqlRowSet rs = jdbcT.queryForRowSet(Ccatalogo.REGIMENES_FISCALES.toString());
			while(rs.next()) {
				regimenesfiscales.add(new Regimenesfiscales(rs.getInt("clave"), rs.getString("descripcion"), rs.getInt("estatus")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regimenesfiscales;
	}

	

}
