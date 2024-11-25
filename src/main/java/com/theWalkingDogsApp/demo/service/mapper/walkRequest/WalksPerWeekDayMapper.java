package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerWeekDayDto;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerWeek;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerWeekDayMapper {
  WalkPerWeek toWalksPerWeekDay(WalksPerWeekDayDto walksPerWeekDay);
  WalksPerDateDto toWalksPerDateDto(WalkPerWeek walksPerWeekDay);
}
