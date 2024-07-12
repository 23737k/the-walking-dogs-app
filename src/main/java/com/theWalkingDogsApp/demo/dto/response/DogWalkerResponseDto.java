package com.theWalkingDogsApp.demo.dto.response;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestDto;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogWalkerResponseDto {
  private Integer id;
  private Schedule schedule;
  private List<WalkRequestDto> walkRequests = new ArrayList<>();
  private List<WalkBooking> walkBookings = new ArrayList<>();
  private Integer ratePerWalk;
  private Set<DogSize> dogSizesAllowed =  new HashSet<>();
  private Integer serviceRadius;
  private boolean isActive = false;
}
