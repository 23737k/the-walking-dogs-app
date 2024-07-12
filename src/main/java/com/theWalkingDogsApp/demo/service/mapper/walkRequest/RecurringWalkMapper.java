package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecurringWalkDto;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalkReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecurringWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  RecurringWalkReq toRecurringWalkReq(RecurringWalkDto recurringWalkDto);

  RecurringWalkDto toRecurringWalkDto (RecurringWalkReq recurringWalkReq);
}
