package com.theWalkingDogsApp.demo.model;

import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import jakarta.persistence.CascadeType;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DogWalker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Schedule schedule;
  @OneToMany
  @JoinColumn(name="dog_walker_id")
  private List<WalkRequest> walkRequests = new ArrayList<>();
  @OneToMany
  @JoinColumn(name = "dog_walker_id")
  private List<WalkBooking> walkBookings = new ArrayList<>();
  private Integer ratePerWalk;
  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "dog_walker_id"))
  @Column(name = "dogSize")
  private Set<DogSize> dogSizesAllowed =  new HashSet<>();
  private Integer serviceRadius;
  private boolean isActive = false;

}
