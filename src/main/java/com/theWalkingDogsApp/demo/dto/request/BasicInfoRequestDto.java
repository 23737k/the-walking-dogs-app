package com.theWalkingDogsApp.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicInfoRequestDto {
  @NotBlank(message = "firstname field must not be empty")
  private String firstname;
  @NotBlank(message = "lastname field must not be empty")
  private String lastname;
  @NotNull(message = "dob field must not be null")
  @Past(message = "dob field must be a date from the past")
  private LocalDate dob;
  @NotBlank(message = "phoneNumber field must not be empty")
  private String phoneNumber;
  @NotBlank(message = "email field must not be empty")
  @Email(message = "email field must have an email format")
  private String email;
}
