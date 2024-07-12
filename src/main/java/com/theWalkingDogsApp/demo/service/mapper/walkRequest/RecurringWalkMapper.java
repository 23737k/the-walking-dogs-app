package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.RecWalkReqDto;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecurringWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  RecurringWalk toRecurringWalkReq(RecWalkReqDto recWalkReqDto);

  RecWalkReqDto toRecurringWalkDto (RecurringWalk recurringWalk);
}
