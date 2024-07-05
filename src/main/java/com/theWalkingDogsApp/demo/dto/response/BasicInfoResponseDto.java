package com.theWalkingDogsApp.demo.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicInfoResponseDto {
  private String firstname;
  private String lastname;
  private LocalDate dob;
  private String phoneNumber;
  private String email;
}
