package com.theWalkingDogsApp.demo.filter;

import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareGiverFilter {
  //recurring o one-time
  private String type;
  private Integer minRatePerWalk;
  private Integer maxRatePerWalk;
  private List<DogSize> dogSizes;
  private List<WeekDay> weekDays;
  private List<LocalDate> dates;
  //private String location;

}


//TODO VALIDACION FUERTE