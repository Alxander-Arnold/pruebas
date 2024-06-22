package com.siifo.siifo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SiifoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiifoApplication.class, args);
	}
}