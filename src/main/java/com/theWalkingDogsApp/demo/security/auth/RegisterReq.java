package com.theWalkingDogsApp.demo.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterReq {
  @Email
  @NotBlank
  private String email;
  @NotBlank
  private String password;

}
