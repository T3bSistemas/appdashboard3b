package appdashboard3b.controladores;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appdashboard3b.beans.Catalogos;
import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.Facturas;
import appdashboard3b.beans.Fclientes;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.PftConexiones;
import appdashboard3b.beans.Rreimpresion;
import appdashboard3b.beans.Solicitudes;
import appdashboard3b.beans.Ticket;
import appdashboard3b.modelos.Mcatalogo;
import appdashboard3b.modelos.Mfactura;
import appdashboard3b.modelos.Mfclientes;
import appdashboard3b.modelos.Mpftconexiones;
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
	Mpftconexiones pc;
	
	@Autowired
	Mreimpresion mr;	
	
	@Autowired
	Msolicitud so;
	
	@Autowired
	Mcatalogo ca;
	
	@GetMapping({"/",""})
	public String inicio(){		
		return "Facturacion T3B Dashboard V2 ";
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<?> cliente(@RequestParam(required = false) String rfc){	
		if(rfc != null && !rfc.equals("")) {
			Pattern pattern = Pattern.compile("^[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(rfc);
		    boolean matchFound = matcher.find();
		    if(matchFound) {
		    	return new ResponseEntity<>(fc.cliente(rfc), HttpStatus.OK);  	
		    }else {
		    	return new ResponseEntity<>("Válida tu RFC sea correcto", HttpStatus.BAD_REQUEST);
		    }							
		}else {
			return new ResponseEntity<>("El parametro RFC no puede ser null o vacio", HttpStatus.BAD_REQUEST);
		}		  
	}
	
	@PostMapping("/setCliente")
	public boolean setCliente(@RequestBody(required = true) Fclientes fclientes){	
		return (fc.cliente(fclientes.getRfc()) != null)?fc.actualizar(fclientes):fc.insertar(fclientes);
	}
	
	@PostMapping("/existeFactura")
	public ExisteFactura existeFactura(@RequestBody(required = true) Ticket ticket){	
		return fa.existeFactura(ticket);
	}
	
	@PostMapping("/pftConexiones")
	public PftConexiones pftConexiones(@RequestParam(required = false) String region){	
		return pc.pftConexiones(region);
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
	public boolean correo(@RequestParam(required = true) String uuid, @RequestParam(required = true) String to, @RequestParam(required = true) String toReply) {			
		 return mr.correo(uuid, to, toReply);
	}
	
	@PostMapping("/catalogos")
	public Catalogos catalogos() {			
		 return ca.Catalogos();
	}
	
}
