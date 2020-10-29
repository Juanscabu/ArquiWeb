package application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Viaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String ciudadDestino;
	@Column(nullable=false)
	private Date fechaInicio;
	@Column(nullable=false)
	private Date fechaFin;
	@Column(nullable=false)
	private String descripcion;
	
	public Viaje() {
	}


}
