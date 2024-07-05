package com.theWalkingDogsApp.demo.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicInfoRequestDto {
  private String firstname;
  private String lastname;
  private LocalDate dob;
  private String phoneNumber;
  private String email;
}
