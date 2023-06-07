package com.api.citasync;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CitasyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasyncApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
				.title("Citasync API")
				.version("v1")
				.description("f1it2-Team-10-Backend-Citasync"));
	}
}

