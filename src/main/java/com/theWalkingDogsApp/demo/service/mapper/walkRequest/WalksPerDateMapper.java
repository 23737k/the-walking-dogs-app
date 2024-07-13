package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.model.walkRequest.WalksPerDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerDateMapper {
  WalksPerDate toWalksPerDate(WalksPerDateDto walkRequest);
  WalkRequestResDto toWalkRequestResDto(WalkRequest walkRequest);
}

