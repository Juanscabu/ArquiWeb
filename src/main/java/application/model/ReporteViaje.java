package application.model;

import java.util.List;

public class ReporteViaje {
	private Usuario usuario;
	private List<Viaje> viajes;


	public ReporteViaje(Usuario usuario, List<Viaje> viajes) {
		this.usuario = usuario;
		this.viajes = viajes;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Viaje> getViajes() {
		return viajes;
	}


	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

}

