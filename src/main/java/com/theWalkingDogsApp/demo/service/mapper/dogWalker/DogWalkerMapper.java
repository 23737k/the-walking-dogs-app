package com.theWalkingDogsApp.demo.service.mapper.dogWalker;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.service.mapper.schedule.ScheduleMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface DogWalkerMapper {
  DogWalker toEntity(DogWalkerReq dogWalkerReq);
  DogWalkerRes toRes(DogWalker dogWalker);
}
