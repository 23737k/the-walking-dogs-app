package com.theWalkingDogsApp.demo.config;

import com.theWalkingDogsApp.demo.bootstrap.Bootstrap;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

   @Bean
  CommandLineRunner commandLineRunner(Bootstrap bootstrap) {
     return args -> {
        bootstrap.init();
     };
   }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("The Walking Dogs App")
            .version("v1.0"));
  }

}
