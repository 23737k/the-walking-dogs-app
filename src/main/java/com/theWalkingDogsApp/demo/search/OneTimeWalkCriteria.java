package com.theWalkingDogsApp.demo.search;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OneTimeWalkCriteria implements SearchCriteria {
  private List<LocalDate> dates;
  private Integer minRatePerWalk;
  private Integer maxRatePerWalk;
  private String location;
  private List<DogSize> dogSizes;

  @Override
  public List<CareGiver> filter(List<CareGiver> careGivers) {
    return careGivers.stream().filter(c-> isActive(c) && isNear(c) && hasRatePerWalkInRange(c) && allowDogSize(c) && isAvailable(c)).toList();
  }

  private boolean isActive(CareGiver careGiver) {
    return careGiver.getDogWalker().isActive();
  }
  private boolean isNear(CareGiver careGiver) {
    //TODO implementar
    return true;
  }

  private boolean hasRatePerWalkInRange(CareGiver careGiver) {
    Integer ratePerWalk = careGiver.getDogWalker().getRatePerWalk();
    return ratePerWalk >= this.minRatePerWalk && ratePerWalk <= this.maxRatePerWalk;
  }

  private boolean allowDogSize(CareGiver careGiver) {
    return new HashSet<>(careGiver.getDogWalker().getDogSizesAllowed()).containsAll(this.dogSizes);
  }

  private boolean isAvailable(CareGiver careGiver) {
    Schedule schedule = careGiver.getDogWalker().getSchedule();
    return dates.stream().allMatch(schedule::isAvailable);
  }
}
