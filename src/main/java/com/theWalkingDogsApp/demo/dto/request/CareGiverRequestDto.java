package com.theWalkingDogsApp.demo.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverRequestDto {
  @Valid
  private BasicInfoRequestDto basicInfo;
  @NotBlank(message = "bio field must not be empty")
  private String bio;
}
