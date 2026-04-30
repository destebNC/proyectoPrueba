package com.example.proyectoPrueba;

import com.fasterxml.jackson.databind.Module; // Importación necesaria
import org.openapitools.jackson.nullable.JsonNullableModule; // Importación necesaria
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPruebaApplication.class, args);
	}

	// AÑADE ESTO AQUÍ (Lo hemos "rescatado" de la clase generada)
	@Bean
	public Module jsonNullableModule() {
		return new JsonNullableModule();
	}
}