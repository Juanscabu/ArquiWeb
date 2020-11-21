package application.utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.model.Usuario;
import application.model.Viaje;
import application.repository.PlanRepository;
import application.repository.UsuarioRepository;
import application.repository.ViajeRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

//    private static final String log = null;
//Carga de datos
	@Bean
    CommandLineRunner initUsuarios(@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
        return args -> {
        	try {
        		CSVParser usuariosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/usuarios.csv"));
        		for(CSVRecord row: usuariosCSV) {
        			System.out.println("Preloading " + usuarioRepository.save(new Usuario(row.get("nombre"),row.get("email"),row.get("contrasenia"))));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
    }
	/*
	@Bean
    CommandLineRunner initViajes(@Qualifier("viajeRepository") ViajeRepository viajeRepository,@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
		  return args -> {
	        	try {
	        		CSVParser viajesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/viajes.csv"));
	        		for(CSVRecord row: viajesCSV) { 
	        			Usuario u = usuarioRepository.findById(Long.parseLong(row.get("usuario"))).get();
	        			System.out.println("Preloading " + viajeRepository.save(new Viaje(row.get("nombre"),row.get("ciudadDestino"),u,new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fechaInicio")).getTime()),new Date(new SimpleDateFormat("dd/mm/yyyy").parse(row.get("fechaFin")).getTime()),row.get("descripcion"))));
	        		}
	        		
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	        };
	    }
	    */
	/*@Bean
    CommandLineRunner initPlanes(@Qualifier("planRepository") PlanRepository billRepository, @Qualifier("clientRepository") ClientRepository clientRepository) {
        return args -> {
        	try {
        		CSVParser billsCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/bills.csv"));
        		for(CSVRecord row: billsCSV) {
        			Client client = clientRepository.getById(Integer.parseInt(row.get("idClient")));
        			System.out.println("Preloading " + billRepository.save(new Bill(Integer.parseInt(row.get("idBill")),new Date(new SimpleDateFormat("yyyy-mm-dd").parse(row.get("date")).getTime()),client)));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
	}*/
}
   