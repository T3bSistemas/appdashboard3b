package appdashboard3b.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/t3b-fact-das")
public class facturacion {
	@GetMapping({"/",""})
	public String inicio(){		
		return "Facturacion T3B Das V 1.0 ";
	}
}
