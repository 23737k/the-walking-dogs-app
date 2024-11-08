package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerWeekDayDto;
import com.theWalkingDogsApp.demo.model.walkRequest.WalksPerWeekDay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerWeekDayMapper {
  WalksPerWeekDay toWalksPerWeekDay(WalksPerWeekDayDto walksPerWeekDay);
  WalksPerDateDto toWalksPerDateDto(WalksPerWeekDay walksPerWeekDay);
}
