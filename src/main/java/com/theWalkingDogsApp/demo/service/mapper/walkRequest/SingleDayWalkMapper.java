package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.SingleDayWalkDto;
import com.theWalkingDogsApp.demo.model.walkRequest.SingleDayWalk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SingleDayWalkMapper{
  SingleDayWalk toSingleDayWalk(SingleDayWalkDto singleDayWalkDto);
}
