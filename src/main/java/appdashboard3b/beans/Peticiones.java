package appdashboard3b.beans;

public class Peticiones {
	Integer id;
	String  num_ticket;
	String  num_tienda;
	String  caja;
	String  fecha_compra;
	String  total;
	String  rfc;
	String  nombre;
	String  email;
	String  cp;
	String  email2;
	String  uso_cfdi;
	String  regimen_fiscal;
	String  fecha_peticion;
	String  estatus;
	
	public Peticiones() {
		super();
	}

	public Peticiones(Integer id, String num_ticket, String num_tienda, String caja, String fecha_compra, String total,
			String rfc, String nombre, String email, String cp, String email2, String uso_cfdi, String regimen_fiscal,
			String fecha_peticion, String estatus) {
		super();
		this.id = id;
		this.num_ticket = num_ticket;
		this.num_tienda = num_tienda;
		this.caja = caja;
		this.fecha_compra = fecha_compra;
		this.total = total;
		this.rfc = rfc;
		this.nombre = nombre;
		this.email = email;
		this.cp = cp;
		this.email2 = email2;
		this.uso_cfdi = uso_cfdi;
		this.regimen_fiscal = regimen_fiscal;
		this.fecha_peticion = fecha_peticion;
		this.estatus = estatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(String num_ticket) {
		this.num_ticket = num_ticket;
	}

	public String getNum_tienda() {
		return num_tienda;
	}

	public void setNum_tienda(String num_tienda) {
		this.num_tienda = num_tienda;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public String getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getUso_cfdi() {
		return uso_cfdi;
	}

	public void setUso_cfdi(String uso_cfdi) {
		this.uso_cfdi = uso_cfdi;
	}

	public String getRegimen_fiscal() {
		return regimen_fiscal;
	}

	public void setRegimen_fiscal(String regimen_fiscal) {
		this.regimen_fiscal = regimen_fiscal;
	}

	public String getFecha_peticion() {
		return fecha_peticion;
	}

	public void setFecha_peticion(String fecha_peticion) {
		this.fecha_peticion = fecha_peticion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Peticiones [id=" + id + ", num_ticket=" + num_ticket + ", num_tienda=" + num_tienda + ", caja=" + caja
				+ ", fecha_compra=" + fecha_compra + ", total=" + total + ", rfc=" + rfc + ", nombre=" + nombre
				+ ", email=" + email + ", cp=" + cp + ", email2=" + email2 + ", uso_cfdi=" + uso_cfdi
				+ ", regimen_fiscal=" + regimen_fiscal + ", fecha_peticion=" + fecha_peticion + ", estatus=" + estatus
				+ "]";
	}
	
}
