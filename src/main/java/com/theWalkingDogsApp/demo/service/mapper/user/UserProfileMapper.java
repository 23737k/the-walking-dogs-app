package com.theWalkingDogsApp.demo.service.mapper.user;

import com.theWalkingDogsApp.demo.dto.request.user.UserProfileReq;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toEntity(UserProfileReq req);
}
