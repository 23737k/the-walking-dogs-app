package com.theWalkingDogsApp.demo.service.mapper;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.PetReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.PetRes;
import com.theWalkingDogsApp.demo.model.pet.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    Pet toEntity(PetReq petReq);
    PetRes toRes(Pet pet);
}
