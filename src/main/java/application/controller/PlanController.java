package application.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import application.model.Actividad;
import application.model.Hospedaje;
import application.model.Plan;
import application.model.Vuelo;
import application.repository.ActividadRepository;
import application.repository.HospedajeRepository;
import application.repository.PlanRepository;
import application.repository.VueloRepository;
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("plan")
public class PlanController {

	 @Qualifier("planRepository")
	    @Autowired
	  private final PlanRepository repository;
	  private final VueloRepository repositoryV;
	  private final HospedajeRepository repositoryH;
	  private final ActividadRepository repositoryA;
	

	    public PlanController(@Qualifier("planRepository") PlanRepository repositoryP,@Qualifier("vueloRepository") VueloRepository repositoryV, @Qualifier("hospedajeRepository") HospedajeRepository repositoryH, @Qualifier("actividadRepository") ActividadRepository repositoryA) {
	        this.repository = repositoryP;      
	        this.repositoryV= repositoryV;
	        this.repositoryH= repositoryH;
	        this.repositoryA= repositoryA;	        
	    }
	
	    @GetMapping("/")
	    public ResponseEntity<List<Plan>> getPlanes() {
	    	List<Plan>listaPlanes = repository.findAll();
	    	 if (!listaPlanes.isEmpty())
	    		 return ResponseEntity.ok().body(listaPlanes);
	    		 else {
	    		throw new PlanNotFoundException("No existen planes");
	    			 }
	    		 }
	    
	  
	    
	    @GetMapping("/{id}")
	     public ResponseEntity<Plan> getPlan(@PathVariable Long id) throws Exception { 
	         Optional<Plan> p = repository.findById(id);
	         if (p.isPresent()) 
	        	 return ResponseEntity.ok().body(p.get());
	         else {
	        	 throw new PlanNotFoundException("El plan con id : " + id + "no existe");
	         }
	    }


	    @DeleteMapping("/{id}")
	   public void deletePlan(@PathVariable Long id) {
	    	  Optional<Plan> c = repository.findById(id);
	    	  if (c.isPresent())
	    		  repository.deleteById(id);
	    	  else 
	    		  throw new PlanNotFoundException("El plan a eliminar con ese id no existe: " + id);
	    }
	    
	    @PostMapping("/vuelo")
	    public ResponseEntity<Vuelo> newVuelo(@RequestBody Vuelo v) {
	    	 Optional<Plan> nuevoVuelo = repository.findById(v.getId());
	    	  if (!nuevoVuelo.isPresent())
	    		  return  ResponseEntity.ok().body(repositoryV.save(v));
	    	
	    	  throw new PlanNotFoundException("El vuelo con ese id ya existe : " + v.getId());
	    }
	    
	    @GetMapping("/vuelos")
	    public ResponseEntity<List<Vuelo>> getVuelos() {
	    	List<Vuelo> listaVuelos = repositoryV.findAll();
	    	 if (!listaVuelos.isEmpty())
	    		 return ResponseEntity.ok().body(listaVuelos);
	    		 else {
	    		throw new PlanNotFoundException("No existen Vuelos");
	    			 }
	    		 }
	    
	    @PostMapping("/hospedaje")
	    public ResponseEntity<Hospedaje > newHospedaje(@RequestBody Hospedaje h) {
	   	 Optional<Hospedaje> nuevoHospedaje = repositoryH.findById(h.getId());
	   	 if (!nuevoHospedaje.isPresent())
   		  return  ResponseEntity.ok().body(repositoryH.save(h));
   	
   	  throw new PlanNotFoundException("El vuelo con ese id ya existe : " + h.getId());
	    }
	    
	    @GetMapping("/hospedajes")
	    public ResponseEntity<List<Hospedaje>> getHospedajes() {
	    	List<Hospedaje>listaHospedajes = repositoryH.findAll();
	    	 if (!listaHospedajes.isEmpty())
	    		 return ResponseEntity.ok().body(listaHospedajes);
	    		 else {
	    		throw new PlanNotFoundException("No existen Hospedajes");
	    			 }
	    		 }
	    
	    @PostMapping("/actividad")
	    public ResponseEntity<Actividad> newActividad(@RequestBody Actividad a) {
	    	 Optional<Plan> nuevoActividad = repository.findById(a.getId());
	    	  if (!nuevoActividad.isPresent())
	    		  return  ResponseEntity.ok().body(repositoryA.save(a));
	    	
	    	  throw new PlanNotFoundException("El vuelo con ese id ya existe : " + a.getId());
	    }
	    
	    @GetMapping("/actividades")
	    public ResponseEntity<List<Actividad>> getActividades() {
	    	List<Actividad>listaActividades = repositoryA.findAll();
	    	 if (!listaActividades.isEmpty())
	    		 return ResponseEntity.ok().body(listaActividades);
	    		 else {
	    		throw new PlanNotFoundException("No existen Hospedajes");
	    			 }
	    		 }
	    
	    
	    @SuppressWarnings("serial")
	    @ResponseStatus(HttpStatus.NOT_FOUND)    
	    public static class PlanNotFoundException extends RuntimeException {
	    		private String message;
	    	  public PlanNotFoundException(String exception) {
	    	    super(exception);
	    	    this.message = exception;
	    	  }
	    	  
	    	    public String message() {
	    	        return message;
	    	    }
	    	} 

}
