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
	@ManyToOne
	private Usuario usuario;
	@JsonIgnore
	@OneToMany(mappedBy="viaje", fetch=FetchType.LAZY)
	private List<Plan> planes;
	@Column(nullable=false)
	private Date fechaInicio;
	@Column(nullable=false)
	private Date fechaFin;
	@Column(nullable=false)
	private String descripcion;
	
	public Viaje() {
	}


}
