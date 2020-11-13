package application.model;

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

	
	
}
