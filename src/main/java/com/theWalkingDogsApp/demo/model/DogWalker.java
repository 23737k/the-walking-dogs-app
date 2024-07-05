package com.theWalkingDogsApp.demo.model;

import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DogWalker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToOne
  private Schedule schedule;
  @OneToMany
  @JoinColumn(name="dog_walker_id")
  private List<WalkRequest> walkRequests;
  @OneToMany
  @JoinColumn(name = "dog_walker_id")
  private List<WalkBooking> walkBookings;
  private Integer ratePerWalk;
  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "dog_walker_id"))
  @Column(name = "dogSize")
  private List<DogSize> dogSizesAllowed;
  private Integer serviceRadius;
  private boolean isActive;
}
