package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Actividad;


public interface ActividadRepository extends JpaRepository<Actividad, Long>  {

}
