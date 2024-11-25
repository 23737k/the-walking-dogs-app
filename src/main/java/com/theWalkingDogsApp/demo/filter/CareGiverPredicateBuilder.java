package com.theWalkingDogsApp.demo.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.theWalkingDogsApp.demo.model.dogWalker.QDogWalker;
import com.theWalkingDogsApp.demo.model.schedule.QSchedule;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.pet.DogSize;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CareGiverPredicateBuilder {

  public static BooleanBuilder buildPredicate(CareGiverFilter filter){
    BooleanBuilder predicate = new BooleanBuilder();
    predicate.and(isActive());
    predicate.and(hasRatePerWalkInRange(filter.getMinRatePerWalk(),filter.getMaxRatePerWalk()));
    predicate.and(allowDogSize(filter.getDogSizes()));
    predicate.and(isAvailable(filter));
    return predicate;
  }


  public static BooleanExpression isActive(){
    return QDogWalker.dogWalker.isActive;
  }

  public static BooleanExpression hasRatePerWalkInRange(Integer min, Integer max){
    if (min != null && max != null) {
      return QDogWalker.dogWalker.ratePerWalk.between(min,max);
    }
    else return null;
  }

  public static BooleanBuilder allowDogSize(List<DogSize> dogSizes){
    BooleanBuilder predicate = new BooleanBuilder();
    if(dogSizes != null) {
      for(DogSize dogSize : dogSizes){
        predicate.and(QDogWalker.dogWalker.dogSizesAllowed.contains(dogSize));
      }
    }
    return predicate;
  }

  public static BooleanBuilder isAvailableForDates(List<LocalDate> dates) {
    BooleanBuilder predicate = new BooleanBuilder();
    QDogWalker dogWalker = QDogWalker.dogWalker;
    if (dates != null) {
      for (LocalDate date : dates) {
        WeekDay weekDay = WeekDay.valueOf(date.getDayOfWeek().toString());
        predicate.and(dogWalker.schedule.unavailableDates.contains(date).not());
        predicate.and(dogWalker.schedule.dailyAvailabilities.any().weekDay.eq(weekDay));
      }
    }
    return predicate;
  }

  public static BooleanBuilder isAvailableForWeekDays(List<WeekDay> weekDays) {
    BooleanBuilder predicate = new BooleanBuilder();
    if(weekDays != null) {
      for (WeekDay weekDay : weekDays) {
        predicate.and(QSchedule.schedule.dailyAvailabilities.any().weekDay.eq(WeekDay.valueOf(weekDay.toString())));
      }
    }
    return predicate;
  }

  public static BooleanBuilder isAvailable(CareGiverFilter filter) {
    String type = filter.getType() == null ? "" : filter.getType();
    return switch (type) {
      case "one-time" -> CareGiverPredicateBuilder.isAvailableForDates(filter.getDates());
      case "recurring" -> CareGiverPredicateBuilder.isAvailableForWeekDays(filter.getWeekDays());
      //en teoria QueryDsl maneja nulls
      default -> null;
    };
  }


}


