package com.theWalkingDogsApp.demo.config;

import com.theWalkingDogsApp.demo.bootstrap.Bootstrap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

  @Bean
  CommandLineRunner commandLineRunner(Bootstrap bootstrap) {
     return args -> {
        bootstrap.init();
     };
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
