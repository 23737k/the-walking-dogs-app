package com.theWalkingDogsApp.demo.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.theWalkingDogsApp.demo.model.QDogWalker;
import com.theWalkingDogsApp.demo.model.schedule.QDailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CareGiverPredicate {

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
    return QDogWalker.dogWalker.ratePerWalk.between(min,max);
  }

  public static BooleanBuilder allowDogSize(List<DogSize> dogSizes){
    BooleanBuilder predicate = new BooleanBuilder();
    for(DogSize dogSize : dogSizes){
      predicate.and(QDogWalker.dogWalker.dogSizesAllowed.contains(dogSize));
    }
    return predicate;
  }

  public static BooleanBuilder isAvailableForDates(List<LocalDate> dates) {
    BooleanBuilder predicate = new BooleanBuilder();
    QDogWalker dogWalker = QDogWalker.dogWalker;

    for (LocalDate date : dates) {
      WeekDay weekDay = WeekDay.valueOf(date.getDayOfWeek().toString());
      predicate.and(dogWalker.schedule.unavailableDates.contains(date).not());
      predicate.and(dogWalker.schedule.dailyAvailabilities.any().weekDay.eq(weekDay));
    }
    return predicate;
  }

  public static BooleanBuilder isAvailableForWeekDays(List<WeekDay> weekDays) {
    BooleanBuilder predicate = new BooleanBuilder();
    for (WeekDay weekDay : weekDays) {
      predicate.and(
          QDogWalker.dogWalker.schedule.dailyAvailabilities.any().weekDay.eq(WeekDay.valueOf(weekDay.toString())));
    }
    return predicate;
  }

  public static BooleanBuilder isAvailable(CareGiverFilter filter) {
    String type = filter.getType();
    return switch (type) {
      case "one-time" -> CareGiverPredicate.isAvailableForDates(filter.getDates());
      case "recurring" -> CareGiverPredicate.isAvailableForWeekDays(filter.getWeekDays());
      //en teoria QueryDsl maneja nulls
      default -> null;
    };
  }


}


