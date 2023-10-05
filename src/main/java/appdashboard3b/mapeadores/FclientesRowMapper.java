package appdashboard3b.mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import appdashboard3b.beans.Domicilio;
import appdashboard3b.beans.Fclientes;


public class FclientesRowMapper implements RowMapper<Fclientes>{
	@Override
	public Fclientes mapRow(ResultSet rs, int rowNum) throws SQLException {
		//rs.getInt("ieps")
		return new Fclientes(rs.getString("crfc"), rs.getString("cnombre"), rs.getString("cemail"), rs.getString("cemail2"), rs.getString("regimen_fiscal"), rs.getString("uso_cfdi"), new Domicilio(rs.getString("ccalle"), rs.getString("ccolonia"), rs.getString("ccp"), rs.getString("cestado"), rs.getString("cmunicipio"), rs.getString("cnumext"), rs.getString("cnumint"), rs.getString("cpais"))  );
	}
}
