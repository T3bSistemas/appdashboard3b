package appdashboard3b.interfaces;

import java.util.List;
import java.util.Map;

import appdashboard3b.beans.DetalleTickets;
import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.Ticket;

public interface Ifactura {
	public ExisteFactura existeFactura(Ticket ticket);
	public boolean guardarFactura(GenerarFactura genera);
	public String regionTienda(String fecha, Integer tienda);	
	public DetalleTickets getTicketsDetalles(List<Ticket> tickets);
	public Map<String, String> formasdepago();
}
