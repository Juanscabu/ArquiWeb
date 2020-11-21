package application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String contrasenia;
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Viaje> viajes;
	
	private transient String token;
	
	public Usuario() {
	}

	public Usuario(String nombre, String email, String contrasenia) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
}
