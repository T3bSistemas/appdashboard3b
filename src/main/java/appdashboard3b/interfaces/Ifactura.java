package appdashboard3b.interfaces;

import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.Ticket;

public interface Ifactura {
	public ExisteFactura existeFactura(Ticket ticket);
	public boolean guardarFactura(GenerarFactura genera);
}
