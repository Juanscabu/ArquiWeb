package testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.http.ResponseEntity;

import application.controller.UsuarioController;
import application.model.Usuario;
import application.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioTest {

	private static Usuario u;
	private static UsuarioController uc;
	@Autowired
	private static  UsuarioRepository ur;
	private static Long id;


	@BeforeClass 
	public static void inicializarDatos() { 
		u = new Usuario("Juan","juan@gmail.com","1234"); 
		uc = new UsuarioController(ur);
		id = (long) 1; u.setId(id); 
		System.out.println("entre aca");
	}


	@Test
	public void getUsuarioTest() throws Exception {
		id = (long) 1;
		u.setId(id);
		ResponseEntity<Usuario> r = uc.getUsuario(id);
		Usuario u1 = r.getBody();
		System.out.println("soy u1 "+u1);
		System.out.println("soy u "+u);
		assertEquals(u,u1);
	}

}
