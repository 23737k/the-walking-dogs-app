package com.theWalkingDogsApp.demo.dto.request.walkRequest;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OneTimeReqDto extends WalkRequestDto{
  private List<SingleDayWalkDto> singleDayWalks;
}
