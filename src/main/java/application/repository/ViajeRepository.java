package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import application.model.Viaje;

public interface ViajeRepository  extends JpaRepository<Viaje, Long> {
	
	@Query("SELECT v FROM Viaje v WHERE v.usuario.id= :id")
	public Iterable<Viaje> findByUsuario(Long id);

	@Query("SELECT v FROM Viaje v WHERE v.usuario.id= :id AND v.ciudadDestino= :ubicacion")
	public Iterable<Viaje> findByUsuarioAndUbicacion(Long id, String ubicacion);
}
