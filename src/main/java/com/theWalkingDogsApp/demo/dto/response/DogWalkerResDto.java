package com.theWalkingDogsApp.demo.dto.response;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogWalkerResDto {
  private Integer id;
  private Schedule schedule;
  private List<WalkRequestReqDto> walkRequests = new ArrayList<>();
  private List<WalkBooking> walkBookings = new ArrayList<>();
  private Integer ratePerWalk;
  private Set<DogSize> dogSizesAllowed =  new HashSet<>();
  private Integer serviceRadius;
  private boolean isActive = false;
}
