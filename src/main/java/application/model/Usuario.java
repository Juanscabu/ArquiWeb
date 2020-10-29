package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Usuario() {
	}
}
