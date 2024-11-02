package com.theWalkingDogsApp.demo.dto.response.careGiver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverResDto {
  private Integer id;
  private BasicInfoResDto basicInfo;
  private String bio;
}