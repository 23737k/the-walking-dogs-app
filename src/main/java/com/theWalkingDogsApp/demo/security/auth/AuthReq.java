package com.theWalkingDogsApp.demo.security.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthReq {
  @NotBlank
  private String email;
  @NotBlank
  private String password;

}
