package com.theWalkingDogsApp.demo.dto.request;

import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyAvailabilityRequestDto {
  @NotNull
  private WeekDay weekDay;
  @NotEmpty
  private List<TimeSlot> timeSlots;
}
