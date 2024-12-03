package com.theWalkingDogsApp.demo.model.dogWalker;

import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;

@Getter
@Setter
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
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dogWalker")
  private List<WalkRequest> walkRequests;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dogWalker")
  private List<WalkBooking> walkBookings;
  private Integer ratePerWalk;
  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "dog_walker_id"))
  @Column(name = "dogSize")
  private Set<DogSize> dogSizesAllowed;
  private Integer serviceRadius;
  @Column(length = 512)
  private String bio;
  private boolean isActive = false;

}
