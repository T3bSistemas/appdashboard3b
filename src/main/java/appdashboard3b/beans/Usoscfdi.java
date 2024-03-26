package appdashboard3b.beans;

public class Usoscfdi {
	String value;
	String item;
	Integer status;
	
	public Usoscfdi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Usoscfdi(String value, String item, Integer status) {
		super();
		this.value = value;
		this.item = item;
		this.status = status;
	}


	public String getValue() {
		return value;
	}
	public void setValue(String value) {
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
		return "usoscfdi [value=" + value + ", item=" + item + ", status=" + status + "]";
	}
	
	
}
