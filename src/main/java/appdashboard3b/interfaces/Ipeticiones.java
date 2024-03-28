package appdashboard3b.interfaces;

import appdashboard3b.beans.Peticiones;
import appdashboard3b.beans.Ticket;

public interface Ipeticiones {
	
	public Peticiones existePeticion(Ticket ticket);
	public boolean inserta(Ticket ticket);
	public boolean actualiza(Ticket ticket, String status);
}
