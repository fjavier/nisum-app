package com.demo.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NissumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NissumAppApplication.class, args);
	}

}
