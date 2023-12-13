package com.iot.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "B13", version = "1", description = "API B13"))
//@Import(OpenAPIConfiguration.class) // importando a classe de configuração do OpenAPI

//@EnableOpenApi
@EnableScheduling

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
 
}
