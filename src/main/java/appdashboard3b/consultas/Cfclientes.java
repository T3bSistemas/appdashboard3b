package appdashboard3b.consultas;

public enum Cfclientes {
	
	FClientes,
	ACTUALIZAR,
	INSRTAR;
	
	public String toString() {
		switch(this) {
			case FClientes:
				return "SELECT crfc, cnombre, cemail, ccalle, cnumext, cnumint, ccolonia, cmunicipio, cestado, cpais, ccp, cemail2, ieps, uso_cfdi, regimen_fiscal FROM fclientes WHERE crfc = ? ";
			case ACTUALIZAR:
				return "UPDATE fclientes SET crfc = ?, cnombre = ?, cemail = ?, cemail2 = ?, ccalle = ?, cnumext = ?, cnumint = ?, ccolonia = ?, cmunicipio = ?, cestado = ?, cpais = ?, ccp = ?, ieps = ?, uso_cfdi = ?, regimen_fiscal = ? WHERE crfc = ? ";
			case INSRTAR:
				return "INSERT INTO fclientes (crfc, cnombre, cemail, cemail2, ccalle, cnumext, cnumint, ccolonia, cmunicipio, cestado, cpais, ccp, ieps, uso_cfdi, regimen_fiscal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			default:
				return "";	
		}
	}
	
}
