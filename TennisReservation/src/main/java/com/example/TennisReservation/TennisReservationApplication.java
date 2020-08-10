package com.example.TennisReservation;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.example.TennisReservation.*"})
public class TennisReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisReservationApplication.class, args);
	}


}