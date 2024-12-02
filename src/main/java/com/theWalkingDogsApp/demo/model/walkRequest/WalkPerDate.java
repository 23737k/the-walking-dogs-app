package com.theWalkingDogsApp.demo.model.walkRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WalkPerDate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDate date;
  @ElementCollection
  @Column(name = "walking_hour")
  @CollectionTable(joinColumns = @JoinColumn(name = "single_day_walk_id"))
  private List<LocalTime> walkingHours;

  public WalkPerDate(LocalDate date, List<LocalTime> walkingHours) {
    this.date = date;
    this.walkingHours = walkingHours;
  }
}
