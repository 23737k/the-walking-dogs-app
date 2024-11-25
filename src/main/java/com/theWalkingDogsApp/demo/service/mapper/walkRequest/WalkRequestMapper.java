package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RecurringWalkMapper.class, OneTimeWalkMapper.class})
public interface WalkRequestMapper {

  default WalkRequestRes toWalkRequestResDto(WalkRequest walkRequest) {
    if (walkRequest instanceof OneTimeWalk) {
      return Mappers.getMapper(OneTimeWalkMapper.class).toOneTimeWalkResDto((OneTimeWalk) walkRequest);

    } else if (walkRequest instanceof RecurringWalk) {
      return Mappers.getMapper(RecurringWalkMapper.class).toRecurringWalkResDto((RecurringWalk) walkRequest);
    }
    throw new IllegalArgumentException("Unknown type: " + walkRequest.getClass().getName());
  }

}
