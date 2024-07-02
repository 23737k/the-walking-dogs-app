package com.theWalkingDogsApp.demo.model.schedule;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

  public boolean isAvailable(LocalDate date, List<TimeSlot> timeSlots) {
    if(unavailableDates.contains(date)) {
      return false;
    }
    WeekDay weekDay = WeekDay.valueOf(date.getDayOfWeek().toString());
    if(dailyAvailabilities.stream) {

    }


    return !unavailableDates.contains(date) &&
        date.getDayOfWeek().
        dailyAvailabilities.stream().anyMatch(d->d.isAvailable(timeSlots));
  }

}
