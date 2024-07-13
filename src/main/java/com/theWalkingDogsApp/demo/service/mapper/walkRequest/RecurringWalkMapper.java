package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecWalkReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.RecWalkResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WalksPerWeekDayMapper.class})
public interface RecurringWalkMapper {
  @Mapping(source = "dogWalkerId", target = "dogWalker.id")
  RecurringWalk toRecurringWalkReq(RecWalkReqDto recWalkReqDto);

  @Mapping(target = "dogWalkerId", source = "dogWalker.id")
  RecWalkResDto toRecurringWalkResDto(RecurringWalk recurringWalk);
}
