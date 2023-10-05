package appdashboard3b.consultas;

public enum Cfclientes {
	
	FClientes,
	ACTUALIZAR,
	INSRTAR;
	
	public String toString() {
		switch(this) {
			case FClientes:
				return
				  "SELECT crfc,\r\n"
				+ "cnombre,\r\n"
				+ "cemail,\r\n"
				+ "ccalle,\r\n"
				+ "cnumext,\r\n"
				+ "cnumint,\r\n"
				+ "ccolonia,\r\n"
				+ "cmunicipio,\r\n"
				+ "cestado,\r\n"
				+ "cpais,\r\n"
				+ "ccp,\r\n"
				+ "cemail2,\r\n"
				+ "ieps,\r\n"
				+ "uso_cfdi,\r\n"
				+ "regimen_fiscal\r\n"
				+ "FROM fclientes \r\n"
				+ "WHERE crfc = ?";
			case ACTUALIZAR:
				return
				  "UPDATE fclientes\r\n"
				+ "SET    crfc = ?,\r\n"
				+ "       cnombre = ?,\r\n"
				+ "       cemail = ?,\r\n"
				+ "       cemail2 = ?,\r\n"
				+ "       ccalle = ?,\r\n"
				+ "       cnumext = ?,\r\n"
				+ "       cnumint = ?,\r\n"
				+ "       ccolonia = ?,\r\n"
				+ "       cmunicipio = ?,\r\n"
				+ "       cestado = ?,\r\n"
				+ "       cpais = ?,\r\n"
				+ "       ccp = ?,\r\n"
				+ "       ieps = ?,\r\n"
				+ "       uso_cfdi = ?,\r\n"
				+ "       regimen_fiscal = ?\r\n"
				+ "WHERE  crfc = ? ";
			case INSRTAR:
				return
				  "INSERT INTO fclientes\r\n"
				+ "            (crfc,\r\n"
				+ "             cnombre,\r\n"
				+ "             cemail,\r\n"
				+ "             cemail2,\r\n"
				+ "             ccalle,\r\n"
				+ "             cnumext,\r\n"
				+ "             cnumint,\r\n"
				+ "             ccolonia,\r\n"
				+ "             cmunicipio,\r\n"
				+ "             cestado,\r\n"
				+ "             cpais,\r\n"
				+ "             ccp,\r\n"
				+ "             ieps,\r\n"
				+ "             uso_cfdi,\r\n"
				+ "             regimen_fiscal)\r\n"
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
				+ "            ?,\r\n"
				+ "            ?,\r\n"
				+ "            ?) ";
			default:
				return
				"";	
		}
	}
	
}
