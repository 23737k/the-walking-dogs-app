package com.theWalkingDogsApp.demo.model.careGiver;

import com.theWalkingDogsApp.demo.model.DogWalker;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CareGiver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne(cascade = CascadeType.ALL)
  private BasicInfo basicInfo;
  private String bio;
 @OneToOne
  private DogWalker dogWalker;
}
