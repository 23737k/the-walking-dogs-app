package com.theWalkingDogsApp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TheWalkingDogsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TheWalkingDogsApplication.class, args);
	}

}
