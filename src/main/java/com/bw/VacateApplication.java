package com.bw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VacateApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacateApplication.class, args);
	}
}
