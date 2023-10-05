package appdashboard3b.interfaces;

import appdashboard3b.beans.Fclientes;

public interface Ifclientes {
	Fclientes 	cliente(String rfc);
	boolean 	actualizar(Fclientes fclientes);
}
