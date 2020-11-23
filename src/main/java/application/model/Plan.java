package application.model;
import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Data
public abstract class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column
	private String nombre;
	@Column
	private Date inicio;
	@Column
	private Date fin;
	@ManyToOne
	private Viaje viaje;
	
	public Plan() {
	}

	public Plan( String nombre, Date inicio, Date fin, Viaje viaje) {
		super();

		this.nombre = nombre;
		this.inicio = inicio;
		this.fin = fin;
		this.viaje = viaje;
	}
	
}