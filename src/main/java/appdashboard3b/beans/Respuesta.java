package appdashboard3b.beans;

public class Respuesta {
	private String	xml;
	private String	fechaCompra;
	private Integer	tienda;
	private String	ticket;
	private String	caja;
	private String	folio;
	private String	serie;	

	
	public Respuesta() {
		super();
	}
	
	
	public Respuesta(String xml, String fechaCompra, Integer tienda, String ticket, String caja, String folio,
			String serie) {
		super();
		this.xml = xml;
		this.fechaCompra = fechaCompra;
		this.tienda = tienda;
		this.ticket = ticket;
		this.caja = caja;
		this.folio = folio;
		this.serie = serie;
	}


	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Integer getTienda() {
		return tienda;
	}

	public void setTienda(Integer tienda) {
		this.tienda = tienda;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSerie() {
		return serie;
	}
	
	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "Respuesta [xml=" + xml + ", fechaCompra=" + fechaCompra + ", tienda=" + tienda + ", ticket="
				+ ticket + ", caja=" + caja + ", folio=" + folio + ", serie=" + serie + "]";
	}
}
