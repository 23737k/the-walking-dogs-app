package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RecurringWalkDto extends WalkRequestDto {
  private List<WeekDayWalkDto> weekDayWalks;
  private LocalDate startOfService;
  private LocalDate endOfService;

}
