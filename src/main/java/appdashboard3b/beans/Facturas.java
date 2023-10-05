package appdashboard3b.beans;

public class Facturas {
	private String 	folio_factura;
	private String 	num_ticket;
	private String 	num_tienda;
	private String 	num_region;
	private String 	caja;
	private String 	fecha_compra;
	private String 	fecha_factura;
	private String 	uuid;
	private String 	xml;
	private String 	xmlBase64;
	private String 	serie;
	private String 	rfc;
	private String 	correo;
	private Double 	total;
	private Double 	subtotal;
	private Double 	iva;
	private Double 	ieps;
	private Integer id_factura;
	private String 	pdfBase64;
	
	public Facturas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Facturas(String folio_factura, String num_ticket, String num_tienda, String num_region, String caja,
			String fecha_compra, String fecha_factura, String uuid, String xml, String xmlBase64, String serie, String rfc, String correo,
			Double total, Double subtotal, Double iva, Double ieps, Integer id_factura) {
		super();
		this.folio_factura = folio_factura;
		this.num_ticket = num_ticket;
		this.num_tienda = num_tienda;
		this.num_region = num_region;
		this.caja = caja;
		this.fecha_compra = fecha_compra;
		this.fecha_factura = fecha_factura;
		this.uuid = uuid;
		this.xml = xml;
		this.xmlBase64 = xmlBase64;
		this.serie = serie;
		this.rfc = rfc;
		this.correo = correo;
		this.total = total;
		this.subtotal = subtotal;
		this.iva = iva;
		this.ieps = ieps;
		this.id_factura = id_factura;
	}
	
	public Facturas(String uuid, String serie, String folio_factura, String correo, String fecha_factura, Integer id_factura, String xmlBase64, String pdfBase64) {
		super();
		this.folio_factura = folio_factura;
		this.fecha_factura = fecha_factura;
		this.uuid = uuid;
		this.serie = serie;
		this.correo = correo;
		this.id_factura = id_factura;
		this.xmlBase64 = xmlBase64;
		this.pdfBase64 = pdfBase64;
	}
	
	public Facturas(String uuid, String folio_factura, String num_ticket, String correo, String fecha_factura, String xmlBase64, String pdfBase64) {
		super();
		this.folio_factura = folio_factura;
		this.num_ticket = num_ticket;
		this.fecha_factura = fecha_factura;
		this.uuid = uuid;
		this.correo = correo;
		this.xmlBase64 = xmlBase64;
		this.pdfBase64 = pdfBase64;
	}
	
	public String getFolio_factura() {
		return folio_factura;
	}

	public String getNum_ticket() {
		return num_ticket;
	}

	public String getNum_tienda() {
		return num_tienda;
	}

	public String getNum_region() {
		return num_region;
	}

	public String getCaja() {
		return caja;
	}

	public String getFecha_compra() {
		return fecha_compra;
	}

	public String getFecha_factura() {
		return fecha_factura;
	}

	public String getUuid() {
		return uuid;
	}

	public String getXml() {
		return xml;
	}
	
	public String getXmlBase64() {
		return xmlBase64;
	}

	public void setXmlBase64(String xmlBase64) {
		this.xmlBase64 = xmlBase64;
	}

	public String getSerie() {
		return serie;
	}

	public String getRfc() {
		return rfc;
	}

	public String getCorreo() {
		return correo;
	}

	public Double getTotal() {
		return total;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double getIva() {
		return iva;
	}

	public Double getIeps() {
		return ieps;
	}

	public Integer getId_factura() {
		return id_factura;
	}

	public void setFolio_factura(String folio_factura) {
		this.folio_factura = folio_factura;
	}

	public void setNum_ticket(String num_ticket) {
		this.num_ticket = num_ticket;
	}

	public void setNum_tienda(String num_tienda) {
		this.num_tienda = num_tienda;
	}

	public void setNum_region(String num_region) {
		this.num_region = num_region;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public void setFecha_factura(String fecha_factura) {
		this.fecha_factura = fecha_factura;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public void setIeps(Double ieps) {
		this.ieps = ieps;
	}

	public void setId_factura(Integer id_factura) {
		this.id_factura = id_factura;
	}

	public String getPdfBase64() {
		return pdfBase64;
	}

	public void setPdfBase64(String pdfBase64) {
		this.pdfBase64 = pdfBase64;
	}	
	
	
}
