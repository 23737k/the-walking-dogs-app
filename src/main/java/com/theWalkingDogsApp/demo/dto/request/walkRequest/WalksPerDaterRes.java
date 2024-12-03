package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WalksPerDaterRes {
  @NotNull(message = "date field must not be null")
  @Future(message = "must be a future date")
  private LocalDate date;
  @NotEmpty(message = "walkingHours must not be empty")
  private List<LocalTime> walkingHours;
}
