package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.service.mapper.schedule.ScheduleMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.WalkRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, WalkRequestMapper.class})
public interface DogWalkerMapper {
  DogWalker toEntity(DogWalkerReqDto dogWalkerReqDto);
  DogWalkerRes toRes(DogWalker dogWalker);
}
