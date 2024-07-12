package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OneTimeReqDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalkReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SingleDayWalkMapper.class})
public interface OneTimeWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  OneTimeWalkReq toOneTimeWalkReq (OneTimeReqDto oneTimeReqDto);

  OneTimeReqDto toOneTimeWalkReqDto (OneTimeWalkReq oneTimeWalkReq);


}
