package application.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "VueloId")
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
	

	public Vuelo( String nombre, Date inicio, Date fin, Viaje viaje, Long nroVuelo, String compania, String aeropuertoSalida, String aeropuertoLlegada,
			String codigoReserva, Long tiempoEscalas, String infoAeronave) {
		super( nombre, inicio, fin, viaje);
		this.nroVuelo = nroVuelo;
		this.compania = compania;
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.codigoReserva = codigoReserva;
		this.tiempoEscalas = tiempoEscalas;
		this.infoAeronave = infoAeronave;
	}
	
	public Vuelo (){
		super();
	}
}
