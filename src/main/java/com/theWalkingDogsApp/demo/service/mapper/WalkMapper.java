package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.walk.WalkReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkRes;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalkMapper {
    WalkRes toRes(Walk walk);
    Walk toEntity(WalkReq walkReq);
}
