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

import application.controller.PlanController.PlanNotFoundException;
import application.model.Plan;
import application.model.Usuario;
import application.repository.PlanRepository;
import application.repository.UsuarioRepository;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	 @Qualifier("usuarioRepository")
	    @Autowired
	    private final UsuarioRepository repository;
	

	    public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository repositoryU) {
	        this.repository = repositoryU;      
	    }
	
	    @GetMapping("/")
	    public ResponseEntity<List<Usuario>> getPlanes() {
	    	List<Usuario>listaPlanes = repository.findAll();
	    	 if (!listaPlanes.isEmpty())
	    		 return ResponseEntity.ok().body(listaPlanes);
	    		 else {
	    		throw new PlanNotFoundException("No existen planes");
	    			 }
	    		 }
	    
	    
	    @GetMapping("/{id}")
	     public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) throws Exception { 
	         Optional<Usuario> u = repository.findById(id);
	         if (u.isPresent()) 
	        	 return ResponseEntity.ok().body(u.get());
	         else {
	        	 throw new PlanNotFoundException("El usuario con id : " + id + "no existe");
	         }
	    }
	    
	    @PostMapping("/")
 		public Usuario newUsuario(@RequestBody Usuario u) {
 			Usuario usuario = new Usuario();
 			usuario.setId(u.getId());
 			usuario.setNombre(u.getNombre());
 			usuario.setEmail(u.getEmail());
 			usuario.setContrasenia(u.getContrasenia());
 			return repository.save(usuario);
 		}


	   @PutMapping("/{id}")
	   public ResponseEntity<Usuario> replaceUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {
		   Optional<Usuario> u = repository.findById(id);
	 	  if (u.isPresent()) {
	     			return  ResponseEntity.ok().body(u.map(Usuario -> {
	                 Usuario.setNombre(newUsuario.getNombre());
	                 return repository.save(Usuario);
	             })
	             .orElseGet(() -> {
	            	 newUsuario.setId(id);
	                 return repository.save(newUsuario);
	             }));
	 	  } 
	 	  else {
	 		  throw new PlanNotFoundException("El usuario a modificar con ese id no existe: " + id);
	 	  }
	   }
	    @DeleteMapping("/{id}")
	    void deletePlan(@PathVariable Long id) {
	    	  Optional<Usuario> c = repository.findById(id);
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
