package com.theWalkingDogsApp.demo.model.schedule;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DailyAvailability {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  private WeekDay weekDay;
  @ElementCollection
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
