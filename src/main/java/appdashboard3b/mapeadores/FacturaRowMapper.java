package appdashboard3b.mapeadores;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import appdashboard3b.beans.Facturas;
import appdashboard3b.utelerias.Codificacion;

public class FacturaRowMapper implements RowMapper<Facturas>{
	@Override
	public Facturas mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		if(columnsNumber == 8) {
			return new Facturas(rs.getString("uuid"), rs.getString("serie"), rs.getString("folio_factura"), rs.getString("correo"), rs.getString("fecha_factura"), rs.getInt("id_factura"), new Codificacion().toStringBase64(rs.getString("xml")), rs.getString("pdfBase64"));
		}else if(columnsNumber == 7) {
			return new Facturas(rs.getString("uuid"), rs.getString("folio_factura"), rs.getString("num_ticket"), rs.getString("correo"), rs.getString("fecha_factura"), new Codificacion().toStringBase64(rs.getString("xml")), rs.getString("pdfBase64"));
		}
		return new Facturas();
	}
}
