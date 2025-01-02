package com.theWalkingDogsApp.demo.security.config;

import com.theWalkingDogsApp.demo.exceptions.TokenNotFoundException;
import com.theWalkingDogsApp.demo.security.token.TokenService;
import com.theWalkingDogsApp.demo.service.UserService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@RequiredArgsConstructor
@Configuration
public class AppConfig {
  private final UserService userService;

  @Bean
  public String[] whiteListedUrls(){
    return new String[]{
        "/api/v1/auth/login",
        "/api/v1/auth/register",
        "/api/v1/auth/renew-token",
        "/api/v1/keepAlive",
        "/v2/api-docs",
        "/v3/api-docs",
        "/v3/api-docs/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui/**",
        "/swagger-ui/*",
        "/webjars/**",
        "/swagger-ui.html"
    };
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService(userService));
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserService userService){
    return userService::findUserByEmail;
  }

  @Bean
  public LogoutHandler logoutHandler(TokenService tokenService) throws IOException{
    return (request, response, authentication) -> {
      try {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
          String token = header.substring(7);
          tokenService.revokeToken(token);
        }
        else throw new TokenNotFoundException("Token is null");
      }
      catch (Exception e){
        try {
          response.getWriter().write("Access denied");
          response.setStatus(401);
          response.setContentType("application/json");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    };
  }

}
