package com.theWalkingDogsApp.demo.model.schedule;

import java.util.HashSet;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyAvailability {
  private WeekDay weekDay;
  private List<TimeSlot> timeSlots;

  public DailyAvailability(WeekDay weekDay, List<TimeSlot> timeSlots) {
    this.weekDay = weekDay;
    this.timeSlots = timeSlots;
  }

  public boolean isAvailable(List<TimeSlot> timeSlots) {
    return new HashSet<>(this.timeSlots).containsAll(timeSlots);
  }

}
