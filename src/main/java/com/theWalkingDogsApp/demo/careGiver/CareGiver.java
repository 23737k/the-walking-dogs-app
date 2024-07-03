package com.theWalkingDogsApp.demo.careGiver;

import com.theWalkingDogsApp.demo.model.DogWalker;
import lombok.Data;

@Data
public class CareGiver {
  private BasicInfo basicInfo;
  private String bio;
  private DogWalker dogWalker;
}
