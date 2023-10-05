package appdashboard3b.interfaces;

import java.util.List;

import appdashboard3b.beans.Solicitudes;



public interface Isolicitud {
	public Integer getFolioSol();
	public boolean guardarSol(List<Solicitudes> solicitudes);
}
