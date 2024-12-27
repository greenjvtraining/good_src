package com.example.simpleJpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleJpaApplication.class, args);
	}

}
