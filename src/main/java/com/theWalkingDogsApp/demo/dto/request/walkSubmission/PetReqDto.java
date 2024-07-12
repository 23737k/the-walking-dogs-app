package com.theWalkingDogsApp.demo.dto.request.walkSubmission;

import com.theWalkingDogsApp.demo.model.walkRequest.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetReqDto {
  private String name;
  private String breed;
  private double weighInKg;
  private Sex sex;
  private Integer age;
}
