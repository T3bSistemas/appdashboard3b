package appdashboard3b.consultas;

public enum Cconexiones {
	PFT_CONEXIONES;
	
	public String toString() {
		switch(this) {
			case PFT_CONEXIONES:
				return
				  "SELECT tclave,\r\n"
				+ "       host,\r\n"
				+ "       puerto,\r\n"
				+ "       servicio,\r\n"
				+ "       base,\r\n"
				+ "       driver,\r\n"
				+ "       url\r\n"
				+ "FROM   pft_conexiones pc\r\n"
				+ "WHERE  pc.tclave = ? ";
			default:
				return
				"";	
		}
	}
}
