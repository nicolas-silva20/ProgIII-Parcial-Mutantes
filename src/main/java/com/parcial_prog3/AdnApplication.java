package com.parcial_prog3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import com.parcial_prog3.services.DnaAnalysis;
@SpringBootApplication
public class AdnApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdnApplication.class, args);
	}

	@Bean
	public org.springframework.boot.web.server.WebServerFactoryCustomizer<org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory> containerCustomizer(Environment environment) {
		return factory -> {
			String port = environment.getProperty("PORT");
			if (port != null) {
				factory.setPort(Integer.parseInt(port));
			} else {
				factory.setPort(8080); // Default to port 8080 if PORT is not set
			}
		};
	}

}
