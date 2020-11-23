package application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	

	@Query("SELECT u FROM Usuario u JOIN Viaje v ON u.id = v.usuario.id GROUP BY u.id ORDER BY count(u.id) DESC")
	public Iterable<Usuario> getUsuariosMasViajes();
	
	@Query("SELECT u FROM Usuario u WHERE u.email =:email")
	public  Optional<Usuario> findByEmail(String email);
}
