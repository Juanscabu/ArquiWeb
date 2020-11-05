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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.Plan;
import application.repository.PlanRepository;
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("plan")
public class PlanController {

	 @Qualifier("planRepository")
	    @Autowired
	    private final PlanRepository repository;
	

	    public PlanController(@Qualifier("planRepository") PlanRepository repositoryP) {
	        this.repository = repositoryP;      
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


	   @PutMapping("/{id}")
	   public ResponseEntity<Plan> replaceCliente(@RequestBody Plan newPlan, @PathVariable Long id) {
		   Optional<Plan> c = repository.findById(id);
	 	  if (c.isPresent()) {
	     			return  ResponseEntity.ok().body(c.map(Plan -> {
	                 Plan.setNombre(newPlan.getNombre());
	                 return repository.save(Plan);
	             })
	             .orElseGet(() -> {
	            	 newPlan.setId(id);
	                 return repository.save(newPlan);
	             }));
	 	  } 
	 	  else {
	 		  throw new PlanNotFoundException("El plan a modificar con ese id no existe: " + id);
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
