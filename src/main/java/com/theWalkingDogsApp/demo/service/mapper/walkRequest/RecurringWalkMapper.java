package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecWalkReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.RecWalkResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WalksPerWeekDayMapper.class})
public interface RecurringWalkMapper {
  RecurringWalk toRecurringWalkReq(RecWalkReqDto recWalkReqDto);

  RecWalkResDto toRecurringWalkResDto(RecurringWalk recurringWalk);
}
