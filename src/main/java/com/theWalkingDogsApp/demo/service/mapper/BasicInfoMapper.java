package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.BasicInfoRequestDto;
import com.theWalkingDogsApp.demo.dto.response.BasicInfoResponseDto;
import com.theWalkingDogsApp.demo.model.careGiver.BasicInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicInfoMapper {
  BasicInfoResponseDto toBasicInfoResponseDto(BasicInfo basicInfo);
  BasicInfo toBasicInfo(BasicInfoResponseDto basicInfoResponseDto);
  BasicInfo toBasicInfo(BasicInfoRequestDto basicInfoRequestDto);
}
