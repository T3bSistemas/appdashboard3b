package appdashboard3b.beans;

public class Captura {
	private String 	fechaCompra;
	private int 	tienda;
	private String 	caja;
	private String 	ticket;
	private Double 	importe;
	private String 	region;
	
	public Captura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Captura(String fechaCompra, int tienda, String caja, String ticket, Double importe, String region) {
		super();
		this.fechaCompra = fechaCompra;
		this.tienda = tienda;
		this.caja = caja;
		this.ticket = ticket;
		this.importe = importe;
		this.region = region;
	}
	
	public String getFechaCompra() {
		return fechaCompra;
	}
	public int getTienda() {
		return tienda;
	}
	public String getCaja() {
		return caja;
	}
	public String getTicket() {
		return ticket;
	}
	public Double getImporte() {
		return importe;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public void setTienda(int tienda) {
		this.tienda = tienda;
	}
	public void setCaja(String caja) {
		this.caja = caja;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "Captura [fechaCompra=" + fechaCompra + ", tienda=" + tienda + ", caja=" + caja + ", ticket=" + ticket
				+ ", importe=" + importe + ", region=" + region + "]";
	}	
}
