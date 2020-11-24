package testJUnit;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import application.controller.UsuarioController;
import application.model.Usuario;
import application.repository.UsuarioRepository;

class UsuarioTest {

	private static Usuario u;
	private static UsuarioController uc;
	@Autowired
	private static UsuarioRepository ur;
	private static Long id;
	
	/*@BeforeClass
	public void inicializarDatos(@Qualifier("usuarioRepository") UsuarioRepository repositoryU) {
		u = new Usuario("Juan", "juan@gmail.com","1234");
		ur = repositoryU;
		uc = new UsuarioController(ur);
		id = (long) 1;
		u.setId(id);
	}*/
	
	@Test
	void getUsuarioTest(@Qualifier("usuarioRepository") UsuarioRepository repositoryU) throws Exception {
		u = new Usuario("Juan", "juan@gmail.com","1234");
		ur = repositoryU;
		uc = new UsuarioController(ur);
		id = (long) 1;
		u.setId(id);
		ResponseEntity<Usuario> r = uc.getUsuario(id);
		Usuario u1 = r.getBody();
		System.out.println("soy u1 "+u1);
		System.out.println("soy u "+u);
		assertEquals(u.getId(),u1.getId());
	}
	
	@Test
	void insertUsuarioTest(@Qualifier("usuarioRepository") UsuarioRepository repositoryU) throws Exception {
		int cantActual= repositoryU.getCount().size();
		//Luego de agregar
		Usuario u1= new Usuario("Juan", "juan@gmail.com","1234");
		repositoryU.save(u1);
		int cantPosterior=repositoryU.getCount().size();
	System.out.println(cantActual);
	System.out.println(cantPosterior);
		assertEquals(cantActual+1,cantPosterior);
	}

//	@Test
//	void deleteUsuarioTest(@Qualifier("usuarioRepository") UsuarioRepository repositoryU) throws Exception {
//		int cantActual= repositoryU.getCount().size();
//		//Luego de agregar
//		Usuario u1= new Usuario("Juan", "juan@gmail.com","1234");
//		repositoryU.delete(u1);
//		int cantPosterior=repositoryU.getCount().size();
//	System.out.println(cantActual);
//	System.out.println(cantPosterior);
//		assertEquals(cantActual+1,cantPosterior);
//	}
}
