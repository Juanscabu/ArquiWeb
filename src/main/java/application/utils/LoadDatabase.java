package application.utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.model.Actividad;
import application.model.Hospedaje;
import application.model.Usuario;
import application.model.Viaje;
import application.model.Vuelo;
import application.repository.ActividadRepository;
import application.repository.HospedajeRepository;
import application.repository.PlanRepository;
import application.repository.UsuarioRepository;
import application.repository.ViajeRepository;
import application.repository.VueloRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

//    private static final String log = null;
//Carga de datos
//	Usuarios
	/*@Bean
	CommandLineRunner initUsuarios(@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
		return args -> {
			try {
				CSVParser usuariosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/usuarios.csv"));
				for (CSVRecord row : usuariosCSV) {
					System.out.println("Preloading " + usuarioRepository
							.save(new Usuario(row.get("nombre"), row.get("email"), row.get("contrasenia"))));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

	
	@Bean
	CommandLineRunner initViajes(@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
		return args -> {
			try {
				CSVParser viajesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/viajes.csv"));
				for (CSVRecord row : viajesCSV) {
					Usuario u = usuarioRepository.findById(Long.parseLong(row.get("usuario"))).get();
					System.out.println("Preloading "
							+ viajeRepository.save(new Viaje(row.get("nombre"), row.get("ciudadDestino"), u,
									new Date(
											new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fechaInicio")).getTime()),
									new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fechaFin")).getTime()),
									row.get("descripcion"))));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
	 
	// definir viaje primero
//	Plan - Actividades
	@Bean
	CommandLineRunner initActividades(@Qualifier("planRepository") PlanRepository planRepository,
			@Qualifier("actividadRepository") ActividadRepository actividadRepository,@Qualifier("viajeRepository") ViajeRepository viajeRepository) {
		return args -> {
			try {
				CSVParser actividadesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/actividades.csv"));
				for (CSVRecord row : actividadesCSV) {
					Viaje v = viajeRepository.findById(Long.parseLong(row.get("viaje"))).get();
					Actividad a = new Actividad(row.get("nombre"),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("inicio")).getTime()),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fin")).getTime()), v,row.get("ubicacion"));
					System.out.println("Preloading " + actividadRepository.save(a));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
//	Plan - Hospedaje
	@Bean
	CommandLineRunner initHospedajes(@Qualifier("planRepository") PlanRepository planRepository,
			@Qualifier("hospedajeRepository") HospedajeRepository hospedajeRepository,@Qualifier("viajeRepository") ViajeRepository viajeRepository) {
		return args -> {
			try {
				CSVParser hospedajeCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/hospedaje.csv"));
				for (CSVRecord row : hospedajeCSV) {
					Viaje v = viajeRepository.findById(Long.parseLong(row.get("viaje"))).get();
					Hospedaje h = new Hospedaje(row.get("nombre"),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("inicio")).getTime()),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fin")).getTime()), v,Integer.parseInt(row.get("cantHabitaciones")),row.get("boucher"),row.get("ubicacion"));
					System.out.println("Preloading " + hospedajeRepository.save(h));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
	
//	Plan - Vuelo
	@Bean
	CommandLineRunner initVuelos(@Qualifier("planRepository") PlanRepository planRepository,
			@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("vueloRepository") VueloRepository vueloRepository) {
		return args -> {
			try {
				CSVParser vuelosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/vuelos.csv"));
				for (CSVRecord row : vuelosCSV) {
					Viaje v = viajeRepository.findById(Long.parseLong(row.get("viaje"))).get();
					Vuelo vuelo = new Vuelo( row.get("nombre"),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("inicio")).getTime()),
							new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fin")).getTime()), v,
							Long.parseLong(row.get("nroVuelo")), row.get("compania"), row.get("aeropuertoSalida"),
							row.get("aeropuertoLlegada"), row.get("codigoReserva"),
							Long.parseLong(row.get("tiempoEscalas")), row.get("infoAeronave"));
					System.out.println("Preloading " + vueloRepository.save(vuelo));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}*/
}
