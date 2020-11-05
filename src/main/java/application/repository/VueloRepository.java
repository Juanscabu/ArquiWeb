package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Plan;
import application.model.Vuelo;

public interface VueloRepository  extends JpaRepository<Vuelo, Long> {

}
