package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.WalksPerDate;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkPerDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalksPerDateMapper {
  WalkPerDate toSingleDayWalk(WalksPerDate walksPerDate);
}
