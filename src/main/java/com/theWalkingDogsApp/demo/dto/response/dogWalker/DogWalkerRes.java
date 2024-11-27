package com.theWalkingDogsApp.demo.dto.response.dogWalker;

import com.theWalkingDogsApp.demo.dto.response.schedule.ScheduleRes;
import com.theWalkingDogsApp.demo.model.pet.DogSize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class DogWalkerRes {
  private Integer id;
  private ScheduleRes schedule;
  private Integer ratePerWalk;
  private Set<DogSize> dogSizesAllowed =  new HashSet<>();
  private Integer serviceRadius;
  private boolean isActive = false;
}
