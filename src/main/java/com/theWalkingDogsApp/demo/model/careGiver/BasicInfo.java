package com.theWalkingDogsApp.demo.model.careGiver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class BasicInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
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
