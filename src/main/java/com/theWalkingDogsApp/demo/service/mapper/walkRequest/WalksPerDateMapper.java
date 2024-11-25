package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalksPerDateDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerDateMapper {
  WalkPerDate toWalksPerDate(WalksPerDateDto walkRequest);
  WalkRequestRes toWalkRequestResDto(WalkRequest walkRequest);
}

