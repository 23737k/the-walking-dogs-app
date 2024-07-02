package com.theWalkingDogsApp.demo.model;

import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogWalker {
  private Schedule schedule;
}
