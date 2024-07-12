package com.theWalkingDogsApp.demo.dto.request.careGiver;

import com.theWalkingDogsApp.demo.dto.request.schedule.ScheduleReqDto;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogWalkerReqDto {
  @Valid
  @NotNull(message = "schedule field must not be null")
  private ScheduleReqDto schedule;
  @NotNull(message = "ratePerWalk field must not be null")
  @Positive(message = "ratePerWalk field must be a positive number")
  private Integer ratePerWalk;
  @NotEmpty(message = "dogSizesAllowed list must have at least one member")
  private List<DogSize> dogSizesAllowed;
  @NotNull(message = "serviceRadius field must not be null")
  @Positive(message = "serviceRadius field must be a positive number")
  private Integer serviceRadius;
  @NotNull(message = "isActive field must be not null")
  private boolean isActive;
}
