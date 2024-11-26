package com.theWalkingDogsApp.demo.service.mapper.schedule;

import com.theWalkingDogsApp.demo.dto.request.schedule.ScheduleReq;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
  Schedule toEntity(ScheduleReq req);
}
