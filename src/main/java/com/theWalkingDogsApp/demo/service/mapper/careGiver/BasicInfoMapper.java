package com.theWalkingDogsApp.demo.service.mapper.careGiver;

import com.theWalkingDogsApp.demo.dto.request.careGiver.BasicInfoReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.BasicInfoResDto;
import com.theWalkingDogsApp.demo.model.careGiver.BasicInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicInfoMapper {
  BasicInfoResDto toBasicInfoResponseDto(BasicInfo basicInfo);
  BasicInfo toBasicInfo(BasicInfoResDto basicInfoResDto);
  BasicInfo toBasicInfo(BasicInfoReqDto basicInfoReqDto);
}
