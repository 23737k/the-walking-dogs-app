package com.theWalkingDogsApp.demo.service.mapper.schedule;

import com.theWalkingDogsApp.demo.dto.request.schedule.DailyAvailabilityReqDto;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyAvailabilityMapper {
  DailyAvailability toDailyAvailability(DailyAvailabilityReqDto requestDto);
}
