package appdashboard3b.utelerias;

import org.springframework.stereotype.Service;

@Service
public class Mensajes {
	
	public String mensaje(Integer tipo) {
		switch(tipo) {
			case 1:
				return "Por el momento el sistema experimenta problemas , por favor inténtelo más tarde";
			case 2:
				return "Error al validar Ticket, inténtelo más tarde";
			case 3:
				return "Favor de validar que la Razón Social o nombre se encuentre registrado tal y como aparece en su Constancia de Situación Fiscal actualizada";
			case 4:
				return "Error de captura, favor de validar que el Código Postal se encuentre registrado tal y como aparece en su Constancia de Situación Fiscal actualizada";
			case 5:
				return "Error de Captura, favor de validar que el Régimen Fiscal se encuentre registrado tal y como aparece en su Constancia de Situación Fiscal actualizada";
			default:
				return "";
		}
	}
	
}
