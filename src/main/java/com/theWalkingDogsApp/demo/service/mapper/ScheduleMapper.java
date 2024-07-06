package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.ScheduleRequestDto;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
  Schedule toSchedule(ScheduleRequestDto scheduleRequestDto);
}
