package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.repository.DogWalkerRepo;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerFilter;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerSpec;
import com.theWalkingDogsApp.demo.service.mapper.dogWalker.DogWalkerMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DogWalkerService {
    private final DogWalkerRepo dogWalkerRepo;
    private final DogWalkerMapper dogWalkerMapper;
    private final DogWalkerSpec specService;

    public List<DogWalkerRes> getDogWalkers(DogWalkerFilter filter) {
        Specification<DogWalker> spec = specService.getAllSpecification(filter);
        return dogWalkerRepo.findAll(spec).stream().map(dogWalkerMapper::toRes).toList();
    }

    public DogWalkerRes getDogWalkerById(Integer id) {
        return dogWalkerMapper.toRes(dogWalkerRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Dog walker with id: " + id + " not found")));
    }

    @Transactional
    public DogWalkerRes updateDogWalker(Integer id, DogWalkerReq dogWalkerReq) {
        findDogWalkerById(id);
        DogWalker updatedDogWalker = dogWalkerMapper.toEntity(dogWalkerReq);
        updatedDogWalker.setWalkBookings(new ArrayList<>());
        updatedDogWalker.setWalkRequests(new ArrayList<>());
        updatedDogWalker.setId(id);
        return dogWalkerMapper.toRes(dogWalkerRepo.save(updatedDogWalker));
    }

    public boolean isEmpty(){
        return dogWalkerRepo.count() == 0;
    }

    public DogWalker findDogWalkerById(Integer id) {
        return dogWalkerRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Dog walker with id: " + id + " not found"));
    }

    @Transactional
    public void deleteDogWalkerById(Integer id) {
        if(!dogWalkerRepo.existsById(id))
            throw new EntityNotFoundException("Dog walker with id: " + id + " not found");
        dogWalkerRepo.deleteById(id);
    }

    public void deleteAll() {
        dogWalkerRepo.deleteAll();
    }
}
