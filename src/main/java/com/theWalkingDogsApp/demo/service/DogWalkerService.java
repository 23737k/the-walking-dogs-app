package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.repository.DogWalkerRepo;
import com.theWalkingDogsApp.demo.service.mapper.careGiver.DogWalkerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogWalkerService {
    private final DogWalkerRepo dogWalkerRepo;
    private final DogWalkerMapper dogWalkerMapper;

    public List<DogWalkerRes> getDogWalkers() {
        return dogWalkerRepo.findAll().stream().map(dogWalkerMapper::toRes).toList();
    }

    public DogWalkerRes getDogWalkerById(Integer id) {
        return dogWalkerMapper.toRes(dogWalkerRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Dog walker with id: " + id + " not found")));
    }

    public DogWalkerRes updateDogWalker( DogWalkerReq dogWalkerReq) {
        return null;
    }
}
