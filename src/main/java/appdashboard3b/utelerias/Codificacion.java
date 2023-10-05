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
	
	public static void main(String ar[]) {
		System.out.println(new Codificacion().toStringBase64(
				""
				));
	}
}
