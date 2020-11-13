package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "HospedajeId")
public class Hospedaje extends Plan {

	@Column(nullable=false)
	private int cantHabitaciones;
	@Column(nullable=false)
	private String boucher;
	@Column(nullable=false)
	private String ubicacion;

	
}
