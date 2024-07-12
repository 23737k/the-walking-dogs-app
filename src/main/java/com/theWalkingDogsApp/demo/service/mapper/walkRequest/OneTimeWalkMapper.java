package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.OTWalkReqDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WalksPerDateMapper.class})
public interface OneTimeWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  OneTimeWalk toOneTimeWalkReq (OTWalkReqDto OTWalkReqDto);

  OTWalkReqDto toOneTimeWalkReqDto (OneTimeWalk oneTimeWalk);


}
