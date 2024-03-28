package appdashboard3b.beans;

import java.util.List;

public class Catalogos {
	List<Usoscfdi> usoscfdi;
	List<Regimenesfiscales> regimenesfiscales;
	List<Questions>  questions;
	public Catalogos() {
		super();
	}
	public Catalogos(List<Usoscfdi> usoscfdi, List<Regimenesfiscales> regimenesfiscales, List<Questions> questions) {
		super();
		this.usoscfdi = usoscfdi;
		this.regimenesfiscales = regimenesfiscales;
		this.questions = questions;
	}
	public List<Usoscfdi> getUsoscfdi() {
		return usoscfdi;
	}
	public void setUsoscfdi(List<Usoscfdi> usoscfdi) {
		this.usoscfdi = usoscfdi;
	}
	public List<Regimenesfiscales> getRegimenesfiscales() {
		return regimenesfiscales;
	}
	public void setRegimenesfiscales(List<Regimenesfiscales> regimenesfiscales) {
		this.regimenesfiscales = regimenesfiscales;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Catalogos [usoscfdi=" + usoscfdi + ", regimenesfiscales=" + regimenesfiscales + ", questions="
				+ questions + "]";
	}
	
}
