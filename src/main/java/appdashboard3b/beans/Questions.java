package appdashboard3b.beans;

public class Questions {
	Integer id;
	String question;
	String details;
	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Questions(Integer id, String question, String details) {
		super();
		this.id = id;
		this.question = question;
		this.details = details;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Questions [id=" + id + ", question=" + question + ", details=" + details + "]";
	}	
	
}
