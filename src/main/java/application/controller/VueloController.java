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

import application.controller.UsuarioController.PlanNotFoundException;
import application.model.Usuario;
import application.model.Vuelo;
import application.repository.UsuarioRepository;
import application.repository.VueloRepository;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("vuelo")
public class VueloController {

	 @Qualifier("usuarioRepository")
	    @Autowired
	    private final VueloRepository repository;
	

	    public VueloController(@Qualifier("usuarioRepository") VueloRepository repositoryV) {
	        this.repository = repositoryV;      
	    }
	
	    @GetMapping("/")
	    public ResponseEntity<List<Vuelo>> getPlanes() {
	    	List<Vuelo>listaPlanes = repository.findAll();
	    	 if (!listaPlanes.isEmpty())
	    		 return ResponseEntity.ok().body(listaPlanes);
	    		 else {
	    		throw new PlanNotFoundException("No existen planes");
	    			 }
	    		 }
	    
	    @GetMapping("/{id}")
	     public ResponseEntity<Vuelo> getVuelo(@PathVariable Long id) throws Exception { 
	         Optional<Vuelo> u = repository.findById(id);
	         if (u.isPresent()) 
	        	 return ResponseEntity.ok().body(u.get());
	         else {
	        	 throw new PlanNotFoundException("El usuario con id : " + id + "no existe");
	         }
	    }
	    

	   
	   @PutMapping("/{id}")
	   public ResponseEntity<Vuelo> replaceVuelo(@RequestBody Vuelo newVuelo, @PathVariable Long id) {
		   Optional<Vuelo> v = repository.findById(id);
	 	  if (v.isPresent()) {
	     			return  ResponseEntity.ok().body(v.map(Vuelo -> {
	     				Vuelo.setNombre(newVuelo.getNombre());
	                 return repository.save(Vuelo);
	             })
	             .orElseGet(() -> {
	            	 newVuelo.setId(id);
	                 return repository.save(newVuelo);
	             }));
	 	  } 
	 	  else {
	 		  throw new PlanNotFoundException("El usuario a modificar con ese id no existe: " + id);
	 	  }
	   }
	    @DeleteMapping("/{id}")
	    void deletePlan(@PathVariable Long id) {
	    	  Optional<Vuelo> c = repository.findById(id);
	    	  if (c.isPresent())
	    		  repository.deleteById(id);
	    	  else 
	    		  throw new PlanNotFoundException("El usuario a eliminar con ese id no existe: " + id);
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
