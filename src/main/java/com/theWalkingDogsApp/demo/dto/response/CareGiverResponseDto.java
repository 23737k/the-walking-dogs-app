package com.theWalkingDogsApp.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverResponseDto {
  private Integer id;
  private BasicInfoResponseDto basicInfo;
  private String bio;
}
