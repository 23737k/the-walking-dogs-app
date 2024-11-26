package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.service.mapper.schedule.ScheduleMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.WalkRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface DogWalkerMapper {
  DogWalker toEntity(DogWalkerReq dogWalkerReq);
  DogWalkerRes toRes(DogWalker dogWalker);
}
