package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OTWalkReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.OTWalkResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WalksPerDateMapper.class})
public interface OneTimeWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  OneTimeWalk toOneTimeWalkReq (OTWalkReqDto OTWalkReqDto);

  @Mapping(target = "dogWalkerId", source = "dogWalker.id")
  OTWalkResDto toOneTimeWalkResDto (OneTimeWalk oneTimeWalk);


}
