package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WalksPerWeekDayReq {
  @NotNull(message = "weekDay field must not be null")
  private WeekDay weekDay;
  @NotEmpty(message = "walkingHours must not be empty")
  private List<LocalTime> walkingHours;
}
