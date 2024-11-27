package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerWeekDayReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * Response Dto for RecurringWalk class
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class RecurringWalkRes extends WalkRequestRes {
  private List<WalksPerWeekDayReq> walksPerWeekDays;
  private LocalDate startOfService;
  private LocalDate endOfService;
}
