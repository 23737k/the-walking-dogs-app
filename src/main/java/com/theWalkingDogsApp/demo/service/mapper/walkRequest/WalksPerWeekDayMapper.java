package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDaterRes;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerWeekDayReq;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerWeek;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerWeekDayMapper {
  WalkPerWeek toWalksPerWeekDay(WalksPerWeekDayReq walksPerWeekDay);
  WalksPerDaterRes toWalksPerDateDto(WalkPerWeek walksPerWeekDay);
}
