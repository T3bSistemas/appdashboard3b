package appdashboard3b.utelerias;

import org.springframework.stereotype.Service;

@Service
public class TiposSolicitudes {

	public String tipos(Integer tipo) {
		switch(tipo) {
		case 0:
			return "INDETERMINADA";
		case 1:
			return "NOM REC";
		case 2:
			return"CP INVALIDO";
		case 3:
			return "CP INVALIDO";
		case 4:
			return "RFC NO EXISTE";
		case 5:
			return "CFDI INC";
		case 6:
			return "REGMFISC";
		case 7:
			return "CP INVALIDO";
		case 8:
			return "VENTA CERO";
		case 9:
			return "FINALIZADA";
		case 10:
			return "ERROR PAC";
		default:
			return "INDETERMINADA";
		}
	}
}
