package com.theWalkingDogsApp.demo.dto.response.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  Response Dto for OneTimeWalk class
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OTWalkResDto extends WalkRequestResDto{
  private List<WalksPerDateDto> walksPerDates;

}
