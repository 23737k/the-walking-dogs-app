package com.theWalkingDogsApp.demo.model.schedule;


import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Schedule {
  private List<DailyAvailability> dailyAvailabilities;
  private List<LocalDate> unavailableDates;

  public Schedule(List<DailyAvailability> dailyAvailabilities, List<LocalDate> unavailableDates) {
    this.dailyAvailabilities = dailyAvailabilities;
    this.unavailableDates = unavailableDates;
  }
  public void addUnavailableDate(LocalDate date) {
    unavailableDates.add(date);
  }

  public void addDailyAvailability(DailyAvailability dailyAvailability) {
    dailyAvailabilities.add(dailyAvailability);
  }

//  public boolean isAvailable(LocalDate date, TimeSlot timeSlot) {
//    if(unavailableDates.contains(date)) {
//      return false;
//    }
//    WeekDay weekDay = WeekDay.valueOf(date.getDayOfWeek().toString());
//    return this.dailyAvailabilities.stream().filter(d -> d.getWeekDay() == weekDay).anyMatch(d -> d.getTimeSlots().contains(timeSlot));
//  }

  public boolean isAvailable(LocalDate date) {
    WeekDay weekDay = WeekDay.valueOf(date.getDayOfWeek().toString());
    return !unavailableDates.contains(date) && dailyAvailabilities.stream().anyMatch(d -> d.getWeekDay() == weekDay);
  }

  public boolean isAvailableForWeekDay(WeekDay weekDay) {
    return dailyAvailabilities.stream().anyMatch(d -> d.getWeekDay() == weekDay);
  }

  public boolean isAvailable(LocalDate startDate, List<WeekDay> weekDays) {
    for(WeekDay weekDay : weekDays){
      if(!isAvailableForWeekDay(weekDay))
        return false;
      LocalDate unavailableWeekDay = unavailableDates.stream()
          .filter(unavailableDate -> WeekDay.valueOf(unavailableDate.getDayOfWeek().toString()).equals(weekDay)).findAny().orElse(null);

      if(unavailableWeekDay != null) {
        if(unavailableWeekDay.isAfter(startDate))
          return false;
      }
    }
    return true;
  }

}
