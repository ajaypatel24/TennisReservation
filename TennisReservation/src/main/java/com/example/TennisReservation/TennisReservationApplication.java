package com.example.TennisReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.example.TennisReservation.*"})
public class TennisReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisReservationApplication.class, args);
	}


}