package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
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
@Schema(description = "Recurring Walk request")
public class RecurringWalkReq extends WalkRequestReq {
  @Valid
  @NotEmpty(message = "walksPerWeekDays field must not be empty")
  private List<WalksPerWeekDayReq> walksPerWeekDays;
  @NotNull(message = "startOfService field must not be empty")
  @Schema(example = "2025-03-01")
  @Future(message = "startOfService must be a future date")
  private LocalDate startOfService;
  @NotNull(message = "endOfService field must not be empty")
  @Schema(example = "2025-03-30")
  @Future(message = "endOfService must be a future date")
  private LocalDate endOfService;

}
