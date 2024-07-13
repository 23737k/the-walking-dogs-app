package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RecWalkReqDto extends WalkRequestReqDto {
  private List<WalksPerWeekDayDto> walksPerWeekDays;
  private LocalDate startOfService;
  private LocalDate endOfService;

}
