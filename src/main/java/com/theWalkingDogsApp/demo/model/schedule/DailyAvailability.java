package com.theWalkingDogsApp.demo.model.schedule;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class DailyAvailability {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  private WeekDay weekDay;
  @ElementCollection()
  @CollectionTable(joinColumns = @JoinColumn(name = "daily_availability_id"))
  @Column(name = "time_slot")
  private Set<TimeSlot> timeSlots;

  public DailyAvailability(WeekDay weekDay, Set<TimeSlot> timeSlots) {
    this.weekDay = weekDay;
    this.timeSlots = timeSlots;
  }

  public boolean isAvailable(List<TimeSlot> timeSlots) {
    return new HashSet<>(this.timeSlots).containsAll(timeSlots);
  }

}
