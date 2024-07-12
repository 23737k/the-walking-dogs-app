package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.theWalkingDogsApp.demo.model.walkRequest.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetDto {
  private String name;
  private String breed;
  private double weighInKg;
  private Sex sex;
  private Integer age;
}
