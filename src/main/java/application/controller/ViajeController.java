package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.controller.PlanController.PlanNotFoundException;
import application.model.ReporteViaje;
import application.model.Usuario;
import application.model.Viaje;
import application.repository.UsuarioRepository;
import application.repository.ViajeRepository;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("viaje")
public class ViajeController {
	@Qualifier("viajeRepository")
	@Autowired
	private final ViajeRepository repositoryViaje;
	private final UsuarioRepository repositoryUsuario;

	public ViajeController(@Qualifier("viajeRepository") ViajeRepository repositoryViaje,@Qualifier("usuarioRepository") UsuarioRepository repositoryUsuario) {
		this.repositoryViaje = repositoryViaje;     
		this.repositoryUsuario = repositoryUsuario;    
	}  
	
	@GetMapping("/all")
	public ResponseEntity<List<Viaje>> getViajes() {
		List<Viaje> listaViajes = repositoryViaje.findAll();
		if (!listaViajes.isEmpty())
			return ResponseEntity.ok().body(listaViajes);
		else {
			throw new ViajeNotFoundException("No existen viajes");
		}
	}    

	@PostMapping("/")
	public ResponseEntity<Viaje> newViaje(@RequestBody Viaje v) {
		 Optional<Viaje> nuevoViaje = repositoryViaje.findById(v.getId());
   	  	if (!nuevoViaje.isPresent())
   		  return  ResponseEntity.ok().body(repositoryViaje.save(v));
   	
   	  throw new PlanNotFoundException("El usuario con ese id ya existe : " + v.getId());
	}

	@GetMapping("/")
	public ResponseEntity<Iterable<Viaje>> getViaje() throws Exception { 
		Long id = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getDetails());
		Iterable<Viaje> v = repositoryViaje.findByUsuario(id);
		System.out.println(v);
		return ResponseEntity.ok().body(v);

	}


	@GetMapping("/byUsuario/{id}")
	public Iterable<Viaje> getReporte(@PathVariable Long id) throws Exception {
		Optional<Usuario> usuario = repositoryUsuario.findById(id);
		if (usuario.isPresent()) {
			Iterable<Viaje> viajes = repositoryViaje.findByUsuario(id);
			if (!viajes.iterator().hasNext())
				return viajes;
			throw new ViajeNotFoundException("El Usuario de ese id no tiene viajes: " + id);
		}
		else { 
			throw new ViajeNotFoundException("El Usuario de ese id no existe: " + id);
		}
	}
	
	@GetMapping("/byUsuarioAndUbicacion/{id}/{ubicacion}")
	public Iterable<Viaje> getReporte(@PathVariable(name = "id",required = true) Long id,  @PathVariable(name = "ubicacion",required = true) String ubicacion) throws Exception {
		Optional<Usuario> usuario = repositoryUsuario.findById(id);
		if (usuario.isPresent()) {
			Iterable<Viaje> viajes = repositoryViaje.findByUsuarioAndUbicacion(id,ubicacion);
			if (!viajes.iterator().hasNext())
				return viajes;
			throw new ViajeNotFoundException("El Usuario de ese id no tiene viajes: " + id);
		}
		else { 
			throw new ViajeNotFoundException("El Usuario de ese id no existe: " + id);
		}
	}
	
	@GetMapping("/zonasMasVisitadas")
	public ResponseEntity<Iterable<Object>> getZonasMasVisitadas() {
		Iterable<Object> zonasVisitadas = repositoryViaje.getZonasMasVisitadas();
		if (zonasVisitadas.iterator().hasNext())
			return ResponseEntity.ok().body(zonasVisitadas);
		else {
			throw new ViajeNotFoundException("No existen viajes");
		}
	}

	@DeleteMapping("/{id}")
	void deleteViaje(@PathVariable Long id) {
		Optional<Viaje> v = repositoryViaje.findById(id);
		if (v.isPresent())
			repositoryViaje.deleteById(id);
		else 
			throw new ViajeNotFoundException("El viaje a eliminar con ese id no existe: " + id);
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)    
	public static class ViajeNotFoundException extends RuntimeException {
		private String message;
		public ViajeNotFoundException(String exception) {
			super(exception);
			this.message = exception;
		}

		public String message() {
			return message;
		}
	}     


}