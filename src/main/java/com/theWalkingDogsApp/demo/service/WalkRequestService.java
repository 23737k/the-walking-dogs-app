package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.exceptions.ForbiddenAccessException;
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
public class WalkRequestService {
  private final WalkRequestRepo repo;
  private final WalkRequestMapper mapper;


  @Transactional
  public List<WalkRequestRes> getDogOwnerRequests(User user) {
    List<WalkRequest> walkRequests = repo.findAllByDogOwner(user.getDogOwner());
    return walkRequests.stream().map(mapper::toRes).toList();
  }
  @Transactional
  public List<WalkRequestRes> getDogWalkerRequests(User user) {
    List<WalkRequest> walkRequests = repo.findAllByDogWalker(user.getDogWalker());
    return walkRequests.stream().map(mapper::toRes).toList();
  }


  @Transactional
  public WalkRequestRes add(User user, WalkRequestReq req) {
    var walkRequest = mapper.toEntity(req);
    walkRequest.setDogOwner(user.getDogOwner());
    return  mapper.toRes(repo.save(walkRequest));
  }

  @Transactional
  public void deleteWalkRequest(User user, Integer id) {
    if(!repo.existsById(id))
      throw new EntityNotFoundException("WalkRequest with id " + id + " not found");
    List<WalkRequest> walkRequests = repo.findAllByDogOwner(user.getDogOwner());
    if(walkRequests.stream().noneMatch(w -> w.getId().equals(id)))
      throw new ForbiddenAccessException("You are not allowed to access this resource");
    repo.deleteById(id);
  }

  @Transactional
  public void deleteAllWalkRequests(User user){
    repo.deleteAllByDogWalkerId(user.getDogWalker().getId());
    repo.deleteAllByDogOwnerId(user.getDogOwner().getId());
  }

  public WalkRequest findById(Integer id){
    return repo.findById(id).orElseThrow(()-> new EntityNotFoundException("WalkRequest with id " + id + " not found"));
  }


}
