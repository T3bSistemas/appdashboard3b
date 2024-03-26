package appdashboard3b.utelerias;

import java.util.Base64;

public class Codificacion {
	
	public String toStringBase64(String data) {
		try {
			return  Base64.getEncoder().encodeToString(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String toBase64String(String data) {
		String cadenaDecodificada = "";
		try {
			byte[] bytesDecodificados = Base64.getDecoder().decode(data);
	        	   cadenaDecodificada = new String(bytesDecodificados);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cadenaDecodificada;
	}
}
