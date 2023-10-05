package appdashboard3b.consultas;

public enum Cfactura {
	Facturas,
	FolioSol,
	FolioSolU,
	Solicitud,
	ISolicitud,
	IFacturas,
	IFacturasD,
	IFacturasT;
	
	public String toString() {
		switch(this) {
			case Facturas:
				return
				    "SELECT folio_factura,\r\n"
				  + "       num_ticket,\r\n"
				  + "       num_tienda,\r\n"
				  + "       num_region,\r\n"
				  + "       caja,\r\n"
				  + "       fecha_compra,\r\n"
				  + "       fecha_factura,\r\n"
				  + "       uuid,\r\n"
				  + "       xml,\r\n"
				  + "       serie,\r\n"
				  + "       rfc,\r\n"
				  + "       correo,\r\n"
				  + "       total,\r\n"
				  + "       subtotal,\r\n"
				  + "       iva,\r\n"
				  + "       ieps,\r\n"
				  + "       id_factura\r\n"
				  + "FROM   facturas\r\n"
				  + "WHERE  num_region = ?\r\n"
				  + "       AND num_tienda = ?\r\n"
				  + "       AND num_ticket = ?\r\n"
				  + "       AND caja = ?\r\n"
				  + "       AND fecha_compra = ? ";
			case FolioSol:
				return
				   "SELECT folio\r\n"
				  + "FROM   folios\r\n"
				  + "WHERE  documento='SOL'";
			case FolioSolU:
				return
				    "UPDATE folios\r\n"
				  + "SET    folio = folio + 1\r\n"
				  + "WHERE  documento = 'SOL' ";
			case Solicitud:
				return
				    "SELECT status_sol,\r\n"
				  + "       rfc,\r\n"
				  + "       tclave,\r\n"
				  + "       num_caja,\r\n"
				  + "       num_ticket,\r\n"
				  + "       fecha_compra,\r\n"
				  + "       fecha_sol,\r\n"
				  + "       descripcion,\r\n"
				  + "       num_factura,\r\n"
				  + "       folio_sol,\r\n"
				  + "       tipo,\r\n"
				  + "       solicitud_id,\r\n"
				  + "       sucursal\r\n"
				  + "FROM   solicitudes\r\n"
				  + "WHERE  tclave = ?\r\n"
				  + "       AND num_caja = ?\r\n"
				  + "       AND num_ticket = ?\r\n"
				  + "       AND fecha_compra = ?\r\n"
				  + "       AND descripcion NOT LIKE '%RFC%'\r\n"
				  + "       AND status_sol <> 3 ";
			case ISolicitud:
				return
				    "INSERT INTO solicitudes\r\n"
				  + "            (fecha_sol,\r\n"
				  + "             status_sol,\r\n"
				  + "             rfc,\r\n"
				  + "             tclave,\r\n"
				  + "             fecha_compra,\r\n"
				  + "             num_caja,\r\n"
				  + "             num_ticket,\r\n"
				  + "             descripcion,\r\n"
				  + "             num_factura,\r\n"
				  + "             folio_sol,\r\n"
				  + "             tipo,\r\n"
				  + "             sucursal)\r\n"
				  + "VALUES     (Getdate(),\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?) ";
			case IFacturas:
				return
				    "INSERT INTO facturas\r\n"
				  + "            (folio_factura,\r\n"
				  + "             num_ticket,\r\n"
				  + "             num_tienda,\r\n"
				  + "             num_region,\r\n"
				  + "             caja,\r\n"
				  + "             fecha_compra,\r\n"
				  + "             fecha_factura,\r\n"
				  + "             uuid,\r\n"
				  + "             xml,\r\n"
				  + "             serie,\r\n"
				  + "             rfc,\r\n"
				  + "             correo,\r\n"
				  + "             total,\r\n"
				  + "             subtotal,\r\n"
				  + "             iva,\r\n"
				  + "             ieps,\r\n"
				  + "             pdfBase64)\r\n"
				  + "VALUES     (?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            CONVERT(date,GETDATE()),\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?) "; 
			case IFacturasD:
				return
				    "INSERT INTO factura_detalle\r\n"
				  + "            (folio_factura,\r\n"
				  + "             serie,\r\n"
				  + "             num_tienda,\r\n"
				  + "             num_region,\r\n"
				  + "             cve_producto,\r\n"
				  + "             cve_prod_sat,\r\n"
				  + "             valor_unit,\r\n"
				  + "             cantidad,\r\n"
				  + "             subtotal,\r\n"
				  + "             ieps_prc,\r\n"
				  + "             ieps_monto,\r\n"
				  + "             iva_prc,\r\n"
				  + "             iva_monto)\r\n"
				  + "VALUES     (?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?,\r\n"
				  + "            ?) ";
			case IFacturasT:
				return
				    "INSERT INTO factura_ticket\r\n"
				  + "            (factura_id,\r\n"
				  + "             fac_det_id,\r\n"
				  + "             idesc)\r\n"
				  + "VALUES      (?,\r\n"
				  + "             ?,\r\n"
				  + "             ?); ";
			default:
				return
				"";	
		}
	}
}
