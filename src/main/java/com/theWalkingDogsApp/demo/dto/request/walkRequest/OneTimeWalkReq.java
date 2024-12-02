package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OneTimeWalkReq extends WalkRequestReq {
  @NotEmpty(message = "walksPerDates field must not be empty")
  @Valid
  private List<WalksPerDaterRes> walksPerDate;
}
