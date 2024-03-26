package appdashboard3b.consultas;

public enum Ccatalogo {
	USOS_CFDI,
	REGIMENES_FISCALES,
	QUESTIONS;
	
	public String toString() {
		switch(this) {
			case USOS_CFDI:
				return
				  "SELECT clave, descripcion, estatus FROM usos_cfdi";
			case REGIMENES_FISCALES:
				return
				  "SELECT clave, descripcion, estatus FROM regimenes_fiscales";
			case QUESTIONS:
				return
				  "SELECT id,question,details,status FROM questions WHERE status = 1";
			default:
				return "";	
		}
	}
}
