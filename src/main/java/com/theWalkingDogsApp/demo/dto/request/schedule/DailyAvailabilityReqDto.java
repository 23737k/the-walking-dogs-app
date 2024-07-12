package com.theWalkingDogsApp.demo.dto.request.schedule;

import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyAvailabilityReqDto {
  @NotNull(message = "weekDay field must not be null")
  private WeekDay weekDay;
  @NotEmpty(message = "timeSlots field must not be empty")
  private List<TimeSlot> timeSlots;
}
