package com.theWalkingDogsApp.demo.dto.request.careGiver;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverReqDto {
  @Valid
  private BasicInfoReqDto basicInfo;
  @NotBlank(message = "bio field must not be empty")
  private String bio;
}
