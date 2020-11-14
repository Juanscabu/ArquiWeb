package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import application.model.Viaje;

public interface ViajeRepository  extends JpaRepository<Viaje, Long> {
	
	@Query("SELECT v FROM Viaje v WHERE v.usuario.id= :id")
	public Iterable<Viaje> findByUsuario(Long id);

	@Query("SELECT v FROM Viaje v WHERE v.usuario.id= :id AND v.ciudadDestino= :ubicacion")
	public Iterable<Viaje> findByUsuarioAndUbicacion(Long id, String ubicacion);
	
	@Query("SELECT v.ciudadDestino, Count(v.ciudadDestino) as cantViajes FROM Viaje v GROUP BY v.ciudadDestino ORDER BY Count(v.ciudadDestino) DESC")
	public Iterable<Object> getZonasMasVisitadas();
}
