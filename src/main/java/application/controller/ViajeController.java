package application.controller;

import java.util.ArrayList;
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
import application.model.Viaje;
import application.repository.ViajeRepository;
/*
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("viajes")
	public class ViajeController {
    @Qualifier("viajeRepository")
    @Autowired
    private final ViajeRepository repositoryViaje;

    public ViajeController(@Qualifier("viajeRepository") ViajeRepository repositoryViaje) {
        this.repositoryViaje = repositoryViaje;      
    }  
    @GetMapping("/")
    public ResponseEntity<List<Viaje>> getViajes() {
    	 List<Viaje> listaViajes = repositoryViaje.findAll();
    	 if (!listaViajes.isEmpty())
    		 return ResponseEntity.ok().body(listaViajes);
    		 else {
    		throw new ViajeNotFoundException("No existen viajes");
    			 }
    		 }    
    @PostMapping("/")
    public Viaje newViaje(@RequestBody Viaje v) {
    	Viaje viaje = new Viaje();
    	viaje.setNombre(v.getNombre());
    	viaje.setCiudadDestino(v.getCiudadDestino());
    	viaje.setDescripcion(v.getDescripcion());
    	viaje.setFechaInicio(v.getFechaInicio());
    	viaje.setFechaFin(v.getFechaFin());
        return repositoryViaje.save(v);
    }
  
    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getViaje(@PathVariable Long id) throws Exception { 
        Optional<Viaje> v = repositoryViaje.findById(id);
        if (v.isPresent()) 
       	 return ResponseEntity.ok().body(v.get());
        else {
       	 throw new ViajeNotFoundException("El viaje de ese id no existe: " + id);
        }
   }
    @PutMapping("/{id}")
    public ResponseEntity<Viaje> replaceViaje(@RequestBody Viaje newViaje, @PathVariable Long id) {
 	   Optional<Viaje> v = repositoryViaje.findById(id);
  	  if (v.isPresent()) {
      			return  ResponseEntity.ok().body(v.map(Viaje -> {
                  Viaje.setNombre(newViaje.getNombre());
                  return repositoryViaje.save(Viaje);
              })
              .orElseGet(() -> {
                  //newCliente.setId(id);
                  return repository.save(newViaje);
              }));
  	  } 
  	  else {
  		  throw new ViajeNotFoundException("El viaje a modificar con ese id no existe: " + id);
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

 */  