package appdashboard3b.beans;

public class Region {
	String numero;
	String nombre;
	String host;
	String puerto;
	String serviceName;
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Region(String numero, String nombre, String host, String puerto, String serviceName) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.host = host;
		this.puerto = puerto;
		this.serviceName = serviceName;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}


	@Override
	public String toString() {
		return "Region [numero=" + numero + ", nombre=" + nombre + ", host=" + host + ", puerto=" + puerto
				+ ", serviceName=" + serviceName + "]";
	}	
	
	
}
