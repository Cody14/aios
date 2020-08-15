package com.smartcard.aios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smartcard.aios")
@EntityScan("com.smartcard.aios.models")
public class AiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiosApplication.class, args);
	}

}
