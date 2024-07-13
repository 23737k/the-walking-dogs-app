package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OTWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestResDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.OneTimeWalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.RecurringWalkMapper;
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


  public List<WalkRequestResDto> getAll() {
    List<WalkRequest> walkRequests = walkRequestRepo.findAll();
    if(walkRequests.isEmpty()) {
      return new ArrayList<>();
    }
    if(walkRequests instanceof RecurringWalk) {
      return walkRequests.stream().map(w -> (WalkRequestResDto) recurringWalkMapper.toRecurringWalkResDto((RecurringWalk) w)).toList();
    }
    else if(walkRequests instanceof OneTimeWalk) {
      return walkRequests.stream().map(w-> (WalkRequestResDto) oneTimeWalkMapper.toOneTimeWalkResDto((OneTimeWalk) w )).toList();
    }
    else
      return new ArrayList<>();
  }

  public WalkRequestResDto add(WalkRequestReqDto walkRequestReqDto) {

    WalkRequestResDto walkRequestResDto;
    WalkRequest walkRequest;

    if(walkRequestReqDto instanceof OTWalkReqDto){
      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OTWalkReqDto) walkRequestReqDto);
      walkRequestResDto = oneTimeWalkMapper.toOneTimeWalkResDto((OneTimeWalk) walkRequestRepo.save(walkRequest));
    }

    else if(walkRequestReqDto instanceof RecWalkReqDto){
      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecWalkReqDto) walkRequestReqDto);
      walkRequestResDto = recurringWalkMapper.toRecurringWalkResDto((RecurringWalk) walkRequestRepo.save(walkRequest));
    }

    else
      throw new IllegalArgumentException("Invalid type of request");
    return walkRequestResDto;
  }


}
