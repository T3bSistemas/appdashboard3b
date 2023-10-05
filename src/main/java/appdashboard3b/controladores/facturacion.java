package appdashboard3b.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.Facturas;
import appdashboard3b.beans.Fclientes;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.Rreimpresion;
import appdashboard3b.beans.Solicitudes;
import appdashboard3b.beans.Ticket;
import appdashboard3b.modelos.Mcorreo;
import appdashboard3b.modelos.Mfactura;
import appdashboard3b.modelos.Mfclientes;
import appdashboard3b.modelos.Mreimpresion;
import appdashboard3b.modelos.Msolicitud;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/t3b-fact-das")
public class facturacion {
	@Autowired
	Mfclientes fc;
	
	@Autowired
	Mfactura fa;
	
	@Autowired
	Mreimpresion mr;
	
	@Autowired
	Mcorreo co;
	
	@Autowired
	Msolicitud so;
	
	@GetMapping({"/",""})
	public String inicio(){		
		return "Facturacion T3B Das V 1.0 ";
	}
	
	@PostMapping("/cliente")
	public Fclientes cliente(@RequestParam(required = true) String rfc){	
		return fc.cliente(rfc);
	}
	
	@PostMapping("/setCliente")
	public boolean setCliente(@RequestParam(required = true) Fclientes fclientes){	
		return (fc.cliente(fclientes.getRfc()) != null)?fc.actualizar(fclientes):fc.insertar(fclientes);
	}
	
	@PostMapping("/existeFactura")
	public ExisteFactura existeFactura(@RequestBody(required = true) Ticket ticket){	
		return fa.existeFactura(ticket);
	}
	
	@PostMapping("/guardarFactura")
	public boolean guardarFactura(@RequestBody(required = true) GenerarFactura genera){	
		return fa.guardarFactura(genera);
	}
	
	@PostMapping("/guardarSolicitud")
	public boolean guardarSolicitud(@RequestBody(required = true) List<Solicitudes> solicitudes){	
		return so.guardarSol(solicitudes);
	}
	
	@PostMapping("/reimpresion")
	public List<Facturas> reimpresion(@RequestBody(required = true) Rreimpresion rreimpresion){	
		return mr.reimpresion(rreimpresion);
	}
	
	@PostMapping("/correo")
	public String correo(@RequestParam(required = true) String uuid) {	
		 co.sendWithAttach("gus2392@hotmail.com", "gap@tiendas3b.com", "prueba", "prueba", "", null);
		 return "";
	}
}
