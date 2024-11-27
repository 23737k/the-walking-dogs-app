package com.theWalkingDogsApp.demo.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RegisterReq {
  @NotBlank
  private String firstname;
  @NotBlank
  private String lastname;
  @NotBlank
  private String phoneNumber;
  @NotNull
  private LocalDate dob;

  @Email
  @NotBlank
  private String email;
  @NotBlank
  private String password;

}
