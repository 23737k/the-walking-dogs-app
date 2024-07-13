package com.theWalkingDogsApp.demo.dto.response.careGiver;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicInfoResDto {
  private String firstname;
  private String lastname;
  private LocalDate dob;
  private String phoneNumber;
  private String email;
}
