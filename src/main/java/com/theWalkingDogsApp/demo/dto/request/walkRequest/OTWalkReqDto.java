package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OTWalkReqDto extends WalkRequestReqDto {
  @NotEmpty(message = "walksPerDates field must not be empty")
  @Valid
  private List<WalksPerDateDto> walksPerDates;
}