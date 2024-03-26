package appdashboard3b.consultas;

public enum Cconexiones {
	PFT_CONEXIONES;
	
	public String toString() {
		switch(this) {
			case PFT_CONEXIONES:
				return
				  "SELECT tclave, host, puerto, servicio, base, driver, url FROM pft_conexiones pc WHERE pc.tclave = ? ";
			default:
				return "";	
		}
	}
}
