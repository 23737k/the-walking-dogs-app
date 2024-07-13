package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerResDto;
import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.service.mapper.schedule.ScheduleMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.OneTimeWalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.RecurringWalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.WalkRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, WalkRequestMapper.class})
public interface DogWalkerMapper {

  DogWalker toDogWalker(DogWalkerReqDto dogWalkerReqDto);
  @Mapping(target = "walkRequests", source = "walkRequests")
  DogWalkerResDto toDogWalkerResponseDto(DogWalker dogWalker);
}
