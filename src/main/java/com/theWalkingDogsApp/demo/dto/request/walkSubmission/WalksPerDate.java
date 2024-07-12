package com.theWalkingDogsApp.demo.dto.request.walkSubmission;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalksPerDate {
  private LocalDate date;
  private List<LocalTime> walkingHours;
}
