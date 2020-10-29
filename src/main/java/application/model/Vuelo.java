package application.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vuelo extends Plan {
	@Column(nullable=false)
	private Long nroVuelo;
	@Column(nullable=false)
	private String compania;
	@Column(nullable=false)
	private String aeropuertoSalida;
	@Column(nullable=false)
	private String aeropuertoLlegada;
	@Column(nullable=false)
	private String codigoReserva;
	@Column
	private Long tiempoEscalas ;
	@Column(nullable=false)
	private String infoAeronave;
	public Vuelo() {
		super();
	}


}
