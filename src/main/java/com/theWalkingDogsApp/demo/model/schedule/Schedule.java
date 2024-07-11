package com.theWalkingDogsApp.demo.model.schedule;


import com.querydsl.core.annotations.QueryEmbedded;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "schedule")
  private List<DailyAvailability> dailyAvailabilities;
  @ElementCollection
  @CollectionTable(name = "unavailable_date", joinColumns = @JoinColumn(name = "schedule_id"))
  @Column(name = "date")
  private Set<LocalDate> unavailableDates;

  public Schedule(List<DailyAvailability> dailyAvailabilities, Set<LocalDate> unavailableDates) {
    this.dailyAvailabilities = filterDuplicatedWeekDays(dailyAvailabilities);
    this.unavailableDates = unavailableDates == null ? new HashSet<>() : unavailableDates;
  }
  public void addUnavailableDate(LocalDate date) {
    unavailableDates.add(date);
  }

  public void addDailyAvailability(DailyAvailability dailyAvailability) {
    dailyAvailabilities.add(dailyAvailability);
  }

  public List<DailyAvailability> filterDuplicatedWeekDays(List<DailyAvailability> dailyAvailabilities){
    if(dailyAvailabilities != null){
      Set<WeekDay> seenWeekDays = new HashSet<>();
      return dailyAvailabilities.stream().filter(d -> seenWeekDays.add(d.getWeekDay())).toList();
    }
    return new ArrayList<>();
  }


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
