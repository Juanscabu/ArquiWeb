package application.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.controller.UsuarioController.UsuarioNotFoundException;
import application.model.Usuario;
import application.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("login")
public class LoginController {
	private final UsuarioRepository repositoryUsuario;	



	public LoginController(@Qualifier("usuarioRepository") UsuarioRepository repositoryUsuario) {
		this.repositoryUsuario = repositoryUsuario;
	}

	@PostMapping("")
	public ResponseEntity<Usuario> login(@RequestParam("email") String email, @RequestParam("contrasenia") String contrasenia) {	
		Optional<Usuario> u = repositoryUsuario.findByEmail(email);
		if (u.isPresent()){
			if (u.get().getContrasenia().equals(contrasenia)) {
				String token = getJWTToken(u.get());
				Usuario user = new Usuario();
				user.setEmail(email);
				user.setToken(token);		
				return ResponseEntity.ok().body(user);
			} else 
			 throw new LoginNotFoundException("La contraseña incorrecta");
		}
		 throw new LoginNotFoundException("No existe un usuario con ese email");
	}


	private String getJWTToken(Usuario u) {
		String secretKey = "mySecretKey";
		String roles = "ROLE_USER";
		String id = Objects.toString(u.getId(), null);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(roles);

		String token = Jwts
				.builder()
				.setId(id)
				.setSubject(u.getEmail())
				.claim("authorities",
						grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)    
	public static class LoginNotFoundException extends RuntimeException {
		private String message;
		public LoginNotFoundException(String exception) {
			super(exception);
			this.message = exception;
		}

		public String message() {
			return message;
		}
	}  
}



