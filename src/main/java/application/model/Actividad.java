package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Actividad extends Plan {
	

	@Column(nullable=false)
	private String ubicacion;

	
	
}
