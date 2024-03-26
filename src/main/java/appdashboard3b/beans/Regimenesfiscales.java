package appdashboard3b.beans;

public class Regimenesfiscales {
	Integer value;
	String item;
	Integer status;
	public Regimenesfiscales() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Regimenesfiscales(Integer value, String item, Integer status) {
		super();
		this.value = value;
		this.item = item;
		this.status = status;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "regimenesfiscales [value=" + value + ", item=" + item + ", status=" + status + "]";
	}
	
	
}
