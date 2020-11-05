package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Plan;
import application.model.Viaje;

public interface ViajeRepository  extends JpaRepository<Viaje, Long> {

}
