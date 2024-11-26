package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OneTimeWalkRes extends WalkRequestRes {
  private List<WalksPerDateDto> walksPerDates;
}
