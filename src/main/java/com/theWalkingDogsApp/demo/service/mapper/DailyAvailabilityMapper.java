package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.DailyAvailabilityRequestDto;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyAvailabilityMapper {
  DailyAvailability toDailyAvailability(DailyAvailabilityRequestDto requestDto);
}
