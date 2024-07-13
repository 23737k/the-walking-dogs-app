package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.CareGiverReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.CareGiverResDto;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BasicInfoMapper.class})
public interface CareGiverMapper {

  CareGiverResDto toCareGiverResponseDto(CareGiver careGiver);
  CareGiver toCareGiver(CareGiverResDto careGiverResDto);
  CareGiver toCareGiver(CareGiverReqDto careGiverReqDto);
}
