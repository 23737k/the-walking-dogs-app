package com.theWalkingDogsApp.demo.model.walkRequest;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SingleDayWalk {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDate date;
  @ElementCollection
  @Column(name = "walking_hour")
  @CollectionTable(joinColumns = @JoinColumn(name = "single_day_walk_id"))
  private List<LocalTime> walkingHours;
}
