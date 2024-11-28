package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.repository.DogWalkerRepo;
import com.theWalkingDogsApp.demo.service.mapper.dogWalker.DogWalkerMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public boolean isEmpty(){
        return dogWalkerRepo.count() == 0;
    }

    public DogWalker findDogWalkerById(Integer id) {
        return dogWalkerRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Dog walker with id: " + id + " not found"));
    }
}
