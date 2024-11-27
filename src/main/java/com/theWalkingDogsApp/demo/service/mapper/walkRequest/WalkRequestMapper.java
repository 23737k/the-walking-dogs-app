package com.theWalkingDogsApp.demo.service.mapper.walkRequest;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OneTimeWalkReq;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecurringWalkReq;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.OneTimeWalkRes;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.RecurringWalkRes;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import static org.mapstruct.SubclassExhaustiveStrategy.RUNTIME_EXCEPTION;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = RUNTIME_EXCEPTION)
public interface WalkRequestMapper {
  @SubclassMapping(source = RecurringWalk.class, target = RecurringWalkRes.class)
  @SubclassMapping(source = OneTimeWalk.class, target = OneTimeWalkRes.class)
  @Mapping(source = "dogWalker.id", target = "dogWalkerId")
  WalkRequestRes toRes(WalkRequest walkRequest);

  @SubclassMapping(target = RecurringWalk.class, source = RecurringWalkReq.class)
  @SubclassMapping(target = OneTimeWalk.class, source = OneTimeWalkReq.class)
  WalkRequest toEntity(WalkRequestReq req);
}
