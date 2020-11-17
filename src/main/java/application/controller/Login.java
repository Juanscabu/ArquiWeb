package application.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Login {

	//Servicio de login
	@PostMapping("/login")
	public Usuario login(@RequestParam("email") String email, @RequestParam("contrasenia") String contrasenia) {
		//En el caso normal debería chequear que el usuario exista.
		
//		if (){
			String token = getJWTToken(email);
			Usuario user = new Usuario();
			user.setEmail(email);
			user.setToken(token);		
			return user;
//		}
//		else {
			
//		}
	}

	//Genero el token.
	private String getJWTToken(String email) {
		String secretKey = "mySecretKey";
		String roles = "ROLE_USER";
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(roles);

		String token = Jwts
				.builder()
				.setId("tokenId")
				.setSubject(email)
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
}

