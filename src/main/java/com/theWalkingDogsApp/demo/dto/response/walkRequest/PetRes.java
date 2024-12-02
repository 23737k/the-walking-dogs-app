package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import com.theWalkingDogsApp.demo.model.pet.Sex;
import lombok.Data;

@Data
public class PetRes {
  private Integer id;
  private String name;
  private String breed;
  private double weighInKg;
  private Sex sex;
  private Integer age;
}
