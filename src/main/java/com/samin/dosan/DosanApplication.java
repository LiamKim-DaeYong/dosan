package com.samin.dosan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class DosanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DosanApplication.class, args);
	}

}
