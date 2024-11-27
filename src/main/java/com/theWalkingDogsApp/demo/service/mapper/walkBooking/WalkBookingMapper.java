package com.theWalkingDogsApp.demo.service.mapper.walkBooking;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkBookingRes;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WalkBookingMapper {
    @Mappings({
            @Mapping(source = "dogOwner.id", target = "dogOwnerId"),
            @Mapping(source = "dogWalker.id", target = "dogWalkerId")
    })
    WalkBookingRes toRes (WalkBooking walkBooking);

}
