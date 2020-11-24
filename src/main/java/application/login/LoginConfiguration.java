package application.login;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //Necesario para que funcione la anotación en el servicio oldman 
public class LoginConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Desactiva el método por defecto
		http.csrf().disable()
		    //Agrega el método de filtrado que codificamos nosotros 
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/swagger-ui/#/").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui/").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/index.html").permitAll()
			.antMatchers(HttpMethod.GET, "/viajes.html").permitAll()
			//.antMatchers(HttpMethod.GET, "/oldman").hasAuthority("LINK") // Esta línea es otra manera de agregar requerimientos de logeo.
			.anyRequest().authenticated();
	}
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                "/swagger-resources",
	                "/swagger-resources/**",
	                "/configuration/ui",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/swagger-ui/**",
	                "/webjars/**");
	    }
	
}
