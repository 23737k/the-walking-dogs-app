package com.theWalkingDogsApp.demo.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleRequestDto {
  @NotEmpty
  @Valid
  private List<DailyAvailabilityRequestDto> dailyAvailabilities;
  private List<LocalDate> unavailableDates;
}
