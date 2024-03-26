package appdashboard3b.consultas;

public enum Creimpresion {
	GETFACRFC,
	GETFACFOLIO,
	GETFACFOLIOSERIE,
	GETFACUUID;
	public String toString() {
		switch(this) {
			case GETFACRFC:
				return "SELECT DISTINCT uuid, serie, folio_factura, correo, fecha_factura, 1 id_factura, convert(varchar(max), xml) xml, pdfBase64 FROM facturas WHERE rfc = ? AND fecha_factura BETWEEN ? AND ? ORDER BY fecha_factura, serie, folio_factura ";
			case GETFACFOLIO:
				return "SELECT DISTINCT uuid, folio_factura, num_ticket, correo, fecha_factura, convert(varchar(max), xml) xml, pdfBase64 FROM facturas WHERE folio_factura = ?  ";
			case GETFACFOLIOSERIE:
				return "SELECT DISTINCT uuid, folio_factura, num_ticket, correo, fecha_factura, convert(varchar(max), xml) xml, pdfBase64 FROM facturas WHERE serie = ? AND folio_factura = ? ORDER BY fecha_factura, uuid ";	
			case GETFACUUID:
				return "SELECT DISTINCT uuid, convert(varchar(max), xml) xml, pdfBase64 FROM facturas WHERE uuid = ? ORDER BY uuid ";	
			default:
				return "";
		}
	}
}
