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
import org.springframework.web.bind.annotation.PutMapping;
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
	    void deletePlan(@PathVariable Long id) {
	    	  Optional<Plan> c = repository.findById(id);
	    	  if (c.isPresent())
	    		  repository.deleteById(id);
	    	  else 
	    		  throw new PlanNotFoundException("El plan a eliminar con ese id no existe: " + id);
	    }
	    
	    @PostMapping("/vuelo")
	    public Vuelo newVuelo(@RequestBody Vuelo v) {
	    	Vuelo vuelo= new Vuelo();
	    	vuelo.setId(v.getId());
	    	vuelo.setNombre(v.getNombre());
	    	vuelo.setNroVuelo(v.getNroVuelo());
	       	vuelo.setCompania(v.getCompania());
	     	vuelo.setAeropuertoSalida(v.getAeropuertoSalida());
	     	vuelo.setAeropuertoLlegada(v.getAeropuertoLlegada());
	     	vuelo.setCodigoReserva(v.getCodigoReserva());
	     	vuelo.setTiempoEscalas(v.getTiempoEscalas());
	        return repositoryV.save(v);
	    }
	    
	    @PostMapping("/hospedaje")
	    public Hospedaje newHospedaje(@RequestBody Hospedaje h) {
	    	Hospedaje hospedaje= new Hospedaje();
	    	hospedaje.setId(h.getId());
	    	hospedaje.setNombre(h.getNombre());
	    	hospedaje.setCantHabitaciones(h.getCantHabitaciones());
	    	hospedaje.setBoucher(h.getBoucher());
	    	hospedaje.setUbicacion(h.getUbicacion());
	    	return repositoryH.save(h);
	    }
	    
	    @PostMapping("/actividad")
	    public Actividad newHospedaje(@RequestBody Actividad a) {
	    	Actividad actividad= new Actividad();
	    	actividad.setId(a.getId());
	    	actividad.setNombre(a.getNombre());
	    	actividad.setUbicacion(a.getUbicacion());
	    	return repositoryA.save(a);
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
