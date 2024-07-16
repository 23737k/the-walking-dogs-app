package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OTWalkReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.OTWalkResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WalksPerDateMapper.class})
public interface OneTimeWalkMapper {
  OneTimeWalk toOneTimeWalkReq (OTWalkReqDto oneTimeWalkReqDto);

  OTWalkResDto toOneTimeWalkResDto (OneTimeWalk oneTimeWalk);


}
