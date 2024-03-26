package appdashboard3b.interfaces;

import java.util.List;

import appdashboard3b.beans.Usoscfdi;
import appdashboard3b.beans.Regimenesfiscales;
import appdashboard3b.beans.Catalogos;
import appdashboard3b.beans.Questions;

public interface Icatalogo {
	public Catalogos Catalogos();
	public List<Questions> Questions();
	public List<Usoscfdi> Usoscfdi();
	public List<Regimenesfiscales> Regimenesfiscales();
}
