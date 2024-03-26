package appdashboard3b.consultas;

public enum Cfactura {
	Facturas,
	FolioSol,
	FolioSolU,
	Solicitud,
	ISolicitud,
	IFacturas,
	IFacturasD,
	IFacturasT,
	RegionFac;
	
	public String toString() {
		switch(this) {
			case Facturas:
				return "SELECT folio_factura, num_ticket, num_tienda, num_region, caja, fecha_compra, fecha_factura, uuid, xml, serie, rfc, correo, total, subtotal, iva, ieps, id_factura FROM facturas WHERE num_region = ? AND num_tienda = ? AND num_ticket = ? AND caja = ? AND fecha_compra = ? ";
			case FolioSol:
				return "SELECT folio FROM folios WHERE documento='SOL'";
			case FolioSolU:
				return "UPDATE folios SET folio = folio + 1 WHERE documento = 'SOL' ";
			case Solicitud:
				return "SELECT status_sol, rfc, tclave, num_caja, num_ticket, fecha_compra, fecha_sol, descripcion, num_factura, folio_sol, tipo, solicitud_id, sucursal FROM solicitudes WHERE tclave = ? AND num_caja = ? AND num_ticket = ? AND fecha_compra = ? AND descripcion NOT LIKE '%RFC%' AND status_sol <> 3 ";
			case ISolicitud:
				return "INSERT INTO solicitudes (fecha_sol, status_sol, rfc, tclave, fecha_compra, num_caja, num_ticket, descripcion, num_factura, folio_sol, tipo, sucursal) VALUES (Getdate(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			case IFacturas:
				return "INSERT INTO facturas (folio_factura, num_ticket, num_tienda, num_region, caja, fecha_compra, fecha_factura, uuid, xml, serie, rfc, correo, total, subtotal, iva, ieps, pdfBase64) VALUES (?, ?, ?, ?, ?, ?, CONVERT(date,GETDATE()), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "; 
			case IFacturasD:
				return "INSERT INTO factura_detalle (folio_factura, serie, num_tienda, num_region, cve_producto, cve_prod_sat, valor_unit, cantidad, subtotal, ieps_prc, ieps_monto, iva_prc, iva_monto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			case IFacturasT:
				return "INSERT INTO factura_ticket (factura_id, fac_det_id, idesc) VALUES (?, ?, ?); "; 
			case RegionFac:
				return "SELECT top 1 num_region FROM Facturas WHERE fecha_factura = CONVERT(date,GETDATE()) AND num_tienda = ?";
			default:
				return "";	
		}
	}
}
