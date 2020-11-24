package application.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "ActividadId")
public class Actividad extends Plan {
	

	@Column(nullable=false)
	private String ubicacion;

	
	public Actividad(String nombre, Date inicio, Date fin, Viaje viaje,  String ubicacion) {
		super(nombre, inicio, fin, viaje);
		this.ubicacion=ubicacion;
	}


	public Actividad() {
		super();
	}	
	
}
