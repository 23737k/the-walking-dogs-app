package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.DogWalkerRequestDto;
import com.theWalkingDogsApp.demo.model.DogWalker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface DogWalkerMapper {

  DogWalker toDogWalker(DogWalkerRequestDto dogWalkerRequestDto);
}
