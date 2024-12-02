package com.theWalkingDogsApp.demo.security.auth;

import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(example = "John")
  private String firstname;
  @NotBlank
  @Schema(example = "Doe")
  private String lastname;
  @NotBlank
  @Schema(example = "123456789")
  private String phoneNumber;
  @NotNull
  @Schema(example = "2000-01-01")
  private LocalDate dob;

  @Email
  @NotBlank
  @Schema(example = "john-doe@gmail.com")
  private String email;
  @NotBlank
  @Schema(example = "P@ssw0rd!")
  private String password;

}
