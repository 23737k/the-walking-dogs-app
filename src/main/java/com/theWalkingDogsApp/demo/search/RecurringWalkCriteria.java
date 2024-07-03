package com.theWalkingDogsApp.demo.search;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecurringWalkCriteria implements SearchCriteria{
  private List<WeekDay> weekDays;
  private LocalDate startDate;
  private String location;
  private Integer ratePerWalk;
  private DogSize dogSize;



  @Override
  public List<CareGiver> filter(List<CareGiver> careGivers) {
    return List.of();
  }
}
