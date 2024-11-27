package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RecurringWalkReq extends WalkRequestReq {
  @Valid
  @NotEmpty(message = "walksPerWeekDays field must not be empty")
  private List<WalksPerWeekDayReq> walksPerWeekDays;
  @NotNull(message = "startOfService field must not be empty")
  private LocalDate startOfService;
  @NotNull(message = "endOfService field must not be empty")
  private LocalDate endOfService;

}
