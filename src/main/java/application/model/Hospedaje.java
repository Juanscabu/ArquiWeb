package application.model;

import java.sql.Date;

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
	
	public Hospedaje(String nombre, Date inicio, Date fin, Viaje viaje, int cantHabitaciones, String boucher, String ubicacion) {
		super(nombre, inicio, fin, viaje);
		this.cantHabitaciones = cantHabitaciones;
		this.boucher = boucher;
		this.ubicacion = ubicacion;
	}
	public Hospedaje (){
		super();
	}
}
