package com.theWalkingDogsApp.demo.config;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(
    title = "The Walking Dogs App"
))
@SecurityScheme(
    type = HTTP,
    name = "bearer-jwt",
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {
}
