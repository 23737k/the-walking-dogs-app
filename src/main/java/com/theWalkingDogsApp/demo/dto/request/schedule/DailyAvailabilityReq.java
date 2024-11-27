package com.theWalkingDogsApp.demo.dto.request.schedule;

import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DailyAvailabilityReq {
  @NotNull(message = "weekDay field must not be null")
  private WeekDay weekDay;
  @NotEmpty(message = "timeSlots field must not be empty")
  private List<TimeSlot> timeSlots;
}
