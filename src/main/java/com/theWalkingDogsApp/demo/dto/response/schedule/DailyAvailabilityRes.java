package com.theWalkingDogsApp.demo.dto.response.schedule;

import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class DailyAvailabilityRes {
  private WeekDay weekDay;
  private Set<TimeSlot> timeSlots;
}
