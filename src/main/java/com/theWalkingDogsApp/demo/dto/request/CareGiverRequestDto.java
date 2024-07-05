package com.theWalkingDogsApp.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverRequestDto {
  private BasicInfoRequestDto basicInfo;
  private String bio;
}
