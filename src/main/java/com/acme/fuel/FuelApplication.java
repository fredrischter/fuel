package com.acme.fuel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class FuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelApplication.class, args);
	}
}
