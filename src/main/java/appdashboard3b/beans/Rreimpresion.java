package appdashboard3b.beans;

public class Rreimpresion {
	private int 	tipo;
	private int 	folio;
	private String 	serie;
	private String 	rfc;

	
	
	public Rreimpresion(int tipo, int folio, String serie, String rfc) {
		super();
		this.tipo = tipo;
		this.folio = folio;
		this.serie = serie;
		this.rfc = rfc;
	}
	public Rreimpresion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTipo() {
		return tipo;
	}
	public int getFolio() {
		return folio;
	}
	public String getSerie() {
		return serie;
	}
	public String getRfc() {
		return rfc;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
}
