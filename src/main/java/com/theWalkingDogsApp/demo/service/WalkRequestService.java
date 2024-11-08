package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OTWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestResDto;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.OneTimeWalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.RecurringWalkMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalkRequestService {
  private final WalkRequestRepo walkRequestRepo;
  private final OneTimeWalkMapper oneTimeWalkMapper;
  private final RecurringWalkMapper recurringWalkMapper;
  private final CareGiverRepo careGiverRepo;
  private final CareGiverService careGiverService;


  public List<WalkRequestResDto> getAll(Integer dogWalkerId) {
    List<WalkRequest> walkRequests = walkRequestRepo.findAllByDogWalkerId(dogWalkerId);
    List<WalkRequestResDto> dtos = new ArrayList<>();

    for (WalkRequest walkRequest : walkRequests) {
      if (walkRequest instanceof RecurringWalk recurringWalk) {
        WalkRequestResDto dto = recurringWalkMapper.toRecurringWalkResDto(recurringWalk);
        dtos.add(dto);
      } else if (walkRequest instanceof OneTimeWalk oneTimeWalk) {
        WalkRequestResDto dto = oneTimeWalkMapper.toOneTimeWalkResDto(oneTimeWalk);
        dtos.add(dto);
      }
    }

    return dtos;
  }

  public WalkRequestResDto add(Integer careGiverId, WalkRequestReqDto walkRequestReqDto) {

    WalkRequestResDto walkRequestResDto;
    WalkRequest walkRequest;
    CareGiver careGiver = careGiverRepo.findById(careGiverId).orElseThrow(()->new EntityNotFoundException("CareGiver not found"));
    DogWalker dogWalker = careGiver.getDogWalker();

    if(walkRequestReqDto instanceof OTWalkReqDto){
      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OTWalkReqDto) walkRequestReqDto);
      walkRequest.setDogWalker(dogWalker);
      walkRequestResDto = oneTimeWalkMapper.toOneTimeWalkResDto((OneTimeWalk) walkRequestRepo.save(walkRequest));
      careGiverRepo.save(careGiver);
    }

    else if(walkRequestReqDto instanceof RecWalkReqDto){
      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecWalkReqDto) walkRequestReqDto);
      walkRequest.setDogWalker(dogWalker);
      walkRequestResDto = recurringWalkMapper.toRecurringWalkResDto((RecurringWalk) walkRequestRepo.save(walkRequest));
      careGiverRepo.save(careGiver);
    }

    else
      throw new IllegalArgumentException("Invalid type of request");
    return walkRequestResDto;
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
