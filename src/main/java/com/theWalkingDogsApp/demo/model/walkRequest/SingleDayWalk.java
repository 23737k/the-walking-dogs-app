package com.theWalkingDogsApp.demo.model.walkRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleDayWalk {
  private LocalDate date;
  private List<LocalTime> walkingHours;
}
