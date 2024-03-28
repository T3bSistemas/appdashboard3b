package appdashboard3b.consultas;

public enum Cpeticiones {
	EXISTE,
	INSERTA,
	ACTUALIZA;
	
	public String toString() {
		switch(this) {
			case EXISTE:
				return "select id,num_ticket,num_tienda,caja,fecha_compra,total,rfc,nombre,email,cp,email2,uso_cfdi,regimen_fiscal,estatus from peticiones where num_ticket = ? and num_tienda= ? and caja= ? and fecha_compra=? and estatus = 'P'";
			case INSERTA:
				return "insert into peticiones (num_ticket,num_tienda,caja,fecha_compra,total,rfc,nombre,email,cp,email2,uso_cfdi,regimen_fiscal,estatus) values (?,?,?,?,?,?,?,?,?,?,?,?,'P')";
			case ACTUALIZA:
			    return "update peticiones set estatus = ? where num_ticket = ? and num_tienda= ? and caja= ? and fecha_compra=?";
			default:
				return "";	
		}
	}
}
