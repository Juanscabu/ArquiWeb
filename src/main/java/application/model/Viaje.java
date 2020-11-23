package application.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	@JsonIgnore
	@OneToMany(mappedBy="viaje", fetch=FetchType.EAGER)
	private transient List<Plan> planes;
	@Column(nullable=false)
	private Date fechaInicio;
	@Column(nullable=false)
	private Date fechaFin;
	@Column(nullable=false)
	private String descripcion;
	
	public Viaje() {
	}

	public Viaje(String nombre, String ciudadDestino, Usuario usuario, Date fechaInicio, Date fechaFin,
			String descripcion) {
		super();
		this.nombre = nombre;
		this.ciudadDestino = ciudadDestino;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
	}	

}
