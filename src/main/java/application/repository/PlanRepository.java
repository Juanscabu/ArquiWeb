package application.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	@Query("SELECT p FROM Plan p WHERE p.viaje.usuario.id= :id")
	Iterable<Plan> findByUsuario(Long id);



}
