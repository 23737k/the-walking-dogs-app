package com.theWalkingDogsApp.demo.search;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OneTimeWalkCriteria implements SearchCriteria {
  private List<LocalDate> dates;
  private Integer ratePerWalk;
  private String location;
  private DogSize dogSize;

  @Override
  public List<CareGiver> filter(List<CareGiver> careGivers) {
    return List.of();
  }
}
