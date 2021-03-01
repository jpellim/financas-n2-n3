package com.example.financasn2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancasN2Application {

	public static void main(String[] args) {
		SpringApplication.run(FinancasN2Application.class, args);
	}

}
