package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.WalkRequestMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class WalkRequestService {
  private final WalkRequestRepo walkRequestRepo;
  private final WalkRequestMapper mapper;


  @Transactional
  public List<WalkRequestRes> getDogOwnerRequests(User user) {
    List<WalkRequest> walkRequests = walkRequestRepo.findAllByDogOwner(user.getDogOwner());
    return walkRequests.stream().map(mapper::toRes).toList();
  }
  @Transactional
  public List<WalkRequestRes> getDogWalkerRequests(User user) {
    List<WalkRequest> walkRequests = walkRequestRepo.findAllByDogWalker(user.getDogWalker());
    return walkRequests.stream().map(mapper::toRes).toList();
  }


  @Transactional
  public WalkRequestRes add(User user, WalkRequestReq req) {
    var walkRequest = mapper.toEntity(req);
    walkRequest.setDogOwner(user.getDogOwner());
    return  mapper.toRes(walkRequestRepo.save(walkRequest));
  }

  public void deleteWalkRequest(Integer id) {
    if(!walkRequestRepo.existsById(id))
      throw new EntityNotFoundException("WalkRequest with id " + id + " not found");
    walkRequestRepo.deleteById(id);
  }

  @Transactional
  public void deleteAllWalkRequests(User user){
    walkRequestRepo.deleteAllByDogWalkerId(user.getDogWalker().getId());
  }

  boolean isEmpty(){
    return walkRequestRepo.count() == 0;
  }

}
