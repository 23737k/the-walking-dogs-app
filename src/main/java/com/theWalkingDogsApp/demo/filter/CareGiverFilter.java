package com.theWalkingDogsApp.demo.filter;

import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CareGiverFilter {
  //recurring o one-time
  private String type;
  @Positive(message = "rate Per Walk must be a positive number")
  private Integer minRatePerWalk;
  @Positive(message = "rate Per Walk must be a positive number")
  private Integer maxRatePerWalk;
  private List<DogSize> dogSizes;
  private List<WeekDay> weekDays;
  @Future(message = "dates field must contain future dates")
  private List<LocalDate> dates;
  //private String location;

}


//TODO VALIDACION FUERTE