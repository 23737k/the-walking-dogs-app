package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.CareGiverRequestDto;
import com.theWalkingDogsApp.demo.dto.response.CareGiverResponseDto;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BasicInfoMapper.class})
public interface CareGiverMapper {

  CareGiverResponseDto toCareGiverResponseDto(CareGiver careGiver);
  CareGiver toCareGiver(CareGiverResponseDto careGiverResponseDto);
  CareGiver toCareGiver(CareGiverRequestDto careGiverRequestDto);
}
