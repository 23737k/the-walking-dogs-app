package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.WalkRequestMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalkRequestService {
  private final WalkRequestRepo walkRequestRepo;
  private final WalkRequestMapper mapper;


  public List<WalkRequestRes> getAll(User user) {
//    List<WalkRequest> walkRequests = walkRequestRepo.findAll();
    List<WalkRequest> walkRequests = walkRequestRepo.findAllByUser(user);
    return walkRequests.stream().map(mapper::toRes).toList();
  }

  public WalkRequestRes add(User user, WalkRequestReq req) {
    WalkRequest walkRequest = mapper.toEntity(req);
    return  mapper.toRes(walkRequestRepo.save(walkRequest));
//
//    if(walkRequestReqDto instanceof OTWalkReqDto){
//      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OTWalkReqDto) walkRequestReqDto);
//      walkRequest.setDogWalker(dogWalker);
//      walkRequestRes = oneTimeWalkMapper.toOneTimeWalkResDto((OneTimeWalk) walkRequestRepo.save(walkRequest));
//      careGiverRepo.save(careGiver);
//    }
//
//    else if(walkRequestReqDto instanceof RecWalkReqDto){
//      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecWalkReqDto) walkRequestReqDto);
//      walkRequest.setDogWalker(dogWalker);
//      walkRequestRes = recurringWalkMapper.toRecurringWalkResDto((RecurringWalk) walkRequestRepo.save(walkRequest));
//      careGiverRepo.save(careGiver);
//    }
//
//    else
//      throw new IllegalArgumentException("Invalid type of request");
//    return walkRequestRes;
  }

  public void deleteWalkRequest(Integer id) {
    validate(id);
    walkRequestRepo.deleteById(id);
  }

  public WalkRequest validate(Integer id){
    return walkRequestRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("WalkRequest with id " + id + " not found"));
  }

  @Transactional
  public void deleteAllWalkRequests(Integer dogWalkerId){
    walkRequestRepo.deleteAllByDogWalkerId(dogWalkerId);
  }
}
