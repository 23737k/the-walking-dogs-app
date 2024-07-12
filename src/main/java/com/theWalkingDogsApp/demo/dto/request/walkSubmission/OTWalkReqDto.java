package com.theWalkingDogsApp.demo.dto.request.walkSubmission;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OTWalkReqDto extends WalkRequestReqDto {
  private List<WalksPerDate> singleDayWalks;
}
