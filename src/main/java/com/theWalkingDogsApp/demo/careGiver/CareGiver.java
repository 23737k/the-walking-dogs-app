package com.theWalkingDogsApp.demo.careGiver;

import com.theWalkingDogsApp.demo.model.DogWalker;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class CareGiver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Transient
  private BasicInfo basicInfo;
  private String bio;
  @Transient
  private DogWalker dogWalker;
}
