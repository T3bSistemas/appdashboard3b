package appdashboard3b.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import appdashboard3b.beans.Catalogos;
import appdashboard3b.beans.DetalleTickets;
import appdashboard3b.beans.ExisteFactura;
import appdashboard3b.beans.Facturas;
import appdashboard3b.beans.GenerarFactura;
import appdashboard3b.beans.Respuesta;
import appdashboard3b.beans.Rreimpresion;
import appdashboard3b.beans.Solicitudes;
import appdashboard3b.beans.Ticket;
import appdashboard3b.modelos.Mcatalogo;
import appdashboard3b.modelos.Mfactura;
import appdashboard3b.modelos.Mfclientes;
import appdashboard3b.modelos.Mpeticiones;
import appdashboard3b.modelos.Mpftconexiones;
import appdashboard3b.modelos.Mreimpresion;
import appdashboard3b.modelos.Msolicitud;
import appdashboard3b.utelerias.Mensajes;
import appdashboard3b.utelerias.TiposSolicitudes;
import appdashboard3b.utelerias.Utilidades;


@CrossOrigin("*")
@RestController
@RequestMapping(path = "/t3b-fact-das")
public class facturacion {
	private static final Logger logger = LoggerFactory.getLogger(facturacion.class);
	
	@Autowired
	Environment env;
	
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
	
	@Autowired
	Utilidades ut;
	
	@Autowired
	Mensajes ms;
	
	@Autowired
	TiposSolicitudes ts;
	
	@Autowired
	Mpeticiones mp;
	
	@GetMapping({"/",""})
	public String inicio() {
		return "Facturacion T3B Dashboard V2.1 ";
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
	
	@PostMapping("/agregarTicket")
	public  ResponseEntity<?> agregarTicket(@RequestBody(required = false) Ticket ticket) {
		String  validarDatos = ut.validarFormatoTicket(ticket);
		if(validarDatos.equals("")) {
			try {
				String region = fa.regionTienda(ticket.getFechaCompra(), ticket.getTienda());
				if(region != null && !region.equals("")) {
					ticket.setRegion(region);
					try {
						ExisteFactura existe = fa.existeFactura(ticket);
						if(existe != null) {
							if(existe.getExiste() == 0) {
								try {
									RestTemplate 					restTemplate	= new RestTemplate();
									HttpEntity<Ticket> 			    request 		= new HttpEntity<Ticket>(ticket);
									ResponseEntity<Ticket>			response 		= restTemplate.exchange(env.getProperty("api.appticket")+"/t3b-fact-ticket/getTicket", HttpMethod.POST, request, new ParameterizedTypeReference<Ticket>(){});
																	mp.ingresaPeticion(ticket, response.getBody());
																	ticket		    = response.getBody();
								} catch (Exception e) {
									logger.error("agregarTicket(4)- "+e.getMessage());
								}
								return new ResponseEntity<>(ticket, HttpStatus.OK);
							} else {
								try {
									if(existe.getTicket().getFolio().contains("F:")) {
										mp.actualiza(ticket, "F");
									}
								} catch (Exception e) {
									logger.error("agregarTicket(3)- "+e.getMessage());
								}
								return new ResponseEntity<>(existe.getTicket(), HttpStatus.OK);
							}
						}else {
							return new ResponseEntity<>(ticket, HttpStatus.OK);
						}
					} catch (Exception e) {
						logger.error("agregarTicket(2)- "+e.getMessage());
					}
				}else {
					return new ResponseEntity<>(ticket, HttpStatus.OK);
				}
			} catch (Exception e) {
				logger.error("agregarTicket(1)- "+e.getMessage());
			}
		}else {
			return new ResponseEntity<>(validarDatos, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ms.mensaje(2), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/generarFactura")
	public List<Respuesta> generarFactura(@RequestBody(required = true) GenerarFactura genera){
		String 			validarFormatoGenerar   =  ut.validarFormatoGenerar(genera);
		if(validarFormatoGenerar.equals("")) {
			try {
				if(fc.cliente(genera.getFclientes().getRfc()) != null){
					fc.actualizar(genera.getFclientes());
				}else {
					fc.insertar(genera.getFclientes());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			DetalleTickets dtTick = fa.getTicketsDetalles(genera.getTickets());
			List<Ticket> tickets  = dtTick.getTickets();
			if(tickets != null && tickets.size() == genera.getTickets().size()) {
				genera.setTickets(tickets);
				try {
					RestTemplate 					restTemplate	= new RestTemplate();
					HttpEntity<GenerarFactura> 		request 		= new HttpEntity<GenerarFactura>(genera);
					ResponseEntity<List<Ticket>> 	response 		= restTemplate.exchange(env.getProperty("api.appfactura")+"/t3b-facturacion/generarFactura", HttpMethod.POST, request, new ParameterizedTypeReference<List<Ticket>>(){});
													tickets 		= response.getBody();
					if(tickets.size() > 0) {
						List<Solicitudes> solicitudes  = new ArrayList<Solicitudes>();
						List<Respuesta>   nofacturados = new ArrayList<Respuesta>();
						List<Respuesta>   facturados   = new ArrayList<Respuesta>();
						List<Respuesta>   todos   	   = new ArrayList<Respuesta>();
						for (Ticket ticket : tickets) {
							if(ticket.getFolio() == null || ticket.getFolio().equals("")){						
								int 	status_sol 	 = 3;
								String 	tipo		 = "INDETERMINADA";
								List<String> errores = fa.erroresPac(ticket.getXml(), ticket.getCodigoError());
								if(errores.size() > 0) {
									ticket.setXml(errores.get(0));
									status_sol= Integer.parseInt(errores.get(1));
									tipo	  = errores.get(2);
								}
								Respuesta respuesta = new Respuesta(ticket.getXml(), ticket.getFechaCompra(), ticket.getTienda(), ticket.getTicket(), ticket.getCaja(), ticket.getFolio(), ticket.getTncrvendflag());
								nofacturados.add(respuesta);
								todos.add(respuesta);
								solicitudes.add(new Solicitudes(status_sol, genera.getFclientes().getRfc(), ticket.getTienda()+"", ticket.getCaja(), ticket.getTicket(), ticket.getFechaCompra(),"", ticket.getXml(), "0", 0, tipo, 0, "T3B"));
							}else {
								Respuesta respuesta = new Respuesta(null, ticket.getFechaCompra(), ticket.getTienda(), ticket.getTicket(), ticket.getCaja(), ticket.getFolio(), ticket.getTncrvendflag());
								facturados.add(respuesta);
								todos.add(respuesta);
								solicitudes.add(new Solicitudes(1, genera.getFclientes().getRfc(), ticket.getTienda()+"", ticket.getCaja(), ticket.getTicket(), ticket.getFechaCompra(),"", "Facturada", ticket.getFolio()+" "+ticket.getTncrvendflag(), 0, "FINALIZADA", 0, "T3B"));
							}
						}
						
						if(solicitudes.size() > 0) {
							try {
								so.guardarSol(solicitudes);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						if(nofacturados.size() == 0) {
							genera.setTickets(tickets);
							try {
								fa.guardarFactura(genera);
								try {
									if(tickets.size() > 0) {
										for (Ticket ticket : tickets) {
											mp.actualiza(ticket, "F");
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							return facturados;
						} else {
							return (facturados.size() == 0)?nofacturados:todos;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();					
				}
			}else {
				if(dtTick.getCodigo() > 0) {				
					
					List<Solicitudes> solicitudes = new ArrayList<Solicitudes>();
					for (Ticket ticket : genera.getTickets()) {
						solicitudes.add(new Solicitudes(3, genera.getFclientes().getRfc(), ticket.getTienda()+"", ticket.getCaja(), ticket.getTicket(), ticket.getFechaCompra(),"", dtTick.getDescripcion(), "0", 0, "INFO. INCOMPLETA", 0, "ERR"));
					}
					if(solicitudes.size() > 0) {
						try {
							 so.guardarSol(solicitudes);
						} catch (Exception e) {
							e.printStackTrace();
						}		
					}
				}
				return Arrays.asList(new Respuesta(ms.mensaje(1)+" 2", null, null, null, null, null, null));
			}
		} else {
			return Arrays.asList(new Respuesta(validarFormatoGenerar, null, null, null, null, null, null));
		}
		return Arrays.asList(new Respuesta(ms.mensaje(1)+" 1", null, null, null, null, null, null));
	}
	
}
