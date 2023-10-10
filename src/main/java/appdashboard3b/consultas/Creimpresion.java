package appdashboard3b.consultas;

public enum Creimpresion {
	GETFACRFC,
	GETFACFOLIO,
	GETFACFOLIOSERIE,
	GETFACUUID;
	public String toString() {
		switch(this) {
			case GETFACRFC:
				return
				  "SELECT DISTINCT uuid,\r\n"
				+ "                serie,\r\n"
				+ "                folio_factura,\r\n"
				+ "                correo,\r\n"
				+ "                fecha_factura,\r\n"
				+ "                1 id_factura,\r\n"
				+ "                convert(varchar(max), xml) xml,\r\n"
				+ "                pdfBase64 \r\n"				
				+ "FROM   facturas\r\n"
				+ "WHERE  rfc = ?\r\n"
				+ "       AND fecha_factura BETWEEN ? AND ?\r\n"
				+ "ORDER  BY fecha_factura,\r\n"
				+ "          serie,\r\n"
				+ "          folio_factura";
			case GETFACFOLIO:
				return
				  "SELECT DISTINCT uuid,\r\n"
				+ "                folio_factura,\r\n"
				+ "                num_ticket,\r\n"
				+ "                correo,\r\n"
				+ "                fecha_factura,\r\n"
				+ "                convert(varchar(max), xml) xml,\r\n"
				+ "                pdfBase64 \r\n"	
				+ "FROM   facturas\r\n"
				+ "WHERE  folio_factura = ? ";
			case GETFACFOLIOSERIE:
				return
				  "SELECT DISTINCT uuid,\r\n"
				+ "                folio_factura,\r\n"
				+ "                num_ticket,\r\n"
				+ "                correo,\r\n"
				+ "                fecha_factura,\r\n"
				+ "                convert(varchar(max), xml) xml,\r\n"
				+ "                pdfBase64 \r\n"	
				+ "FROM   facturas\r\n"
				+ "WHERE  serie = ?\r\n"
				+ "       AND folio_factura = ? \r\n"
				+ "ORDER  BY fecha_factura, uuid";	
			case GETFACUUID:
				return
				  "SELECT DISTINCT uuid,\r\n"
				+ "                convert(varchar(max), xml) xml,\r\n"
				+ "                pdfBase64 \r\n"	
				+ "FROM   facturas\r\n"
				+ "WHERE  uuid = ?\r\n"
				+ "ORDER  BY  uuid";	
			default:
				return 
				"";
		}
	}
}
