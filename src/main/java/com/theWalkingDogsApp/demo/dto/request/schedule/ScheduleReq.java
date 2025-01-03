package com.theWalkingDogsApp.demo.dto.request.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleReq {
  @NotEmpty
  @Valid
  private List<DailyAvailabilityReq> dailyAvailabilities;
  @NotEmpty(message = "unavailableDates field must not be empty")
  private List<LocalDate> unavailableDates;
}
