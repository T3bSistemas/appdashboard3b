package appdashboard3b.interfaces;

import appdashboard3b.beans.Peticiones;
import appdashboard3b.beans.Ticket;

public interface Ipeticiones {
	
	public Peticiones existePeticion(Ticket ticket);
	public Long inserta(Ticket ticket, String estatus);
	public boolean actualiza(Ticket ticket, String status);
	public void ingresaPeticion(Ticket ticket, Ticket ticketR);
}
