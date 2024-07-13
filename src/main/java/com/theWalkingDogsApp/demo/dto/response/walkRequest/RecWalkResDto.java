package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerWeekDayDto;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Response Dto for RecurringWalk class
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class RecWalkResDto extends WalkRequestResDto{
  private List<WalksPerWeekDayDto> walksPerWeekDays;
  private LocalDate startOfService;
  private LocalDate endOfService;
}
