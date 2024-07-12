package com.theWalkingDogsApp.demo.dto.request.walkSubmission;

import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalksPerWeekDay {
  private WeekDay weekDay;
  private List<LocalTime> walkingHours;
}