package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReqDto;
import com.theWalkingDogsApp.demo.dto.response.DogWalkerResDto;
import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.service.mapper.schedule.ScheduleMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface DogWalkerMapper {

  DogWalker toDogWalker(DogWalkerReqDto dogWalkerReqDto);
  DogWalkerResDto toDogWalkerResponseDto(DogWalker dogWalker);
}
