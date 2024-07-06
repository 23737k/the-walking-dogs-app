package com.theWalkingDogsApp.demo.dto.request;

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
public class DogWalkerRequestDto {
  @Valid
  @NotNull
  private ScheduleRequestDto schedule;
  @NotNull
  @Positive
  private Integer ratePerWalk;
  @NotEmpty
  private List<DogSize> dogSizesAllowed;
  @NotNull
  @Positive
  private Integer serviceRadius;
}
