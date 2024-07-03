package com.theWalkingDogsApp.demo.model;

import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogWalker {
  private Schedule schedule;
  private List<WalkRequest> walkRequests;
  private List<WalkBooking> walkBookings;
  private Integer ratePerWalk;
  private List<DogSize> dogSizesAllowed;
  private boolean isActive;
}
