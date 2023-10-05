package appdashboard3b.interfaces;

import java.util.List;

import appdashboard3b.beans.Facturas;
import appdashboard3b.beans.Rreimpresion;

public interface Ireimpresion {
	
	List<Facturas> reimpresion(Rreimpresion rreimpresion);
}
