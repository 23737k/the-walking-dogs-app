package com.theWalkingDogsApp.demo.careGiver;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BasicInfo {
  private String firstname;
  private String lastname;
  private LocalDate dob;
  private String phoneNumber;
  private String email;

  public BasicInfo(String firstname, String lastname, LocalDate dob, String phoneNumber, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.dob = dob;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }


}
