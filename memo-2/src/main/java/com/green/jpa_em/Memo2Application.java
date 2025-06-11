package com.green.jpa_em;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Memo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Memo2Application.class, args);
	}

}
