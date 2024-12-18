package com.theWalkingDogsApp.demo.service.mapper.schedule;

import com.theWalkingDogsApp.demo.dto.request.schedule.DailyAvailabilityReq;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyAvailabilityMapper {
  DailyAvailability toDailyAvailability(DailyAvailabilityReq requestDto);
}
