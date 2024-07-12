package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.OneTimeReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.RecurringWalkDto;
import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalkReq;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalkReq;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.OneTimeWalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkRequest.RecurringWalkMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalkRequestService {
  private final WalkRequestRepo walkRequestRepo;
  private final OneTimeWalkMapper oneTimeWalkMapper;
  private final RecurringWalkMapper recurringWalkMapper;

  public List<WalkRequest> getAll() {
    return walkRequestRepo.findAll();
  }

  public WalkRequestDto add(WalkRequestDto walkRequestDto) {

    WalkRequest walkRequest;
    WalkRequestDto walkRequestDtoFromRepo;

    if(walkRequestDto instanceof OneTimeReqDto){
      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OneTimeReqDto) walkRequestDto);
      walkRequestDtoFromRepo = oneTimeWalkMapper.toOneTimeWalkReqDto((OneTimeWalkReq) walkRequestRepo.save(walkRequest));
    }
    else if(walkRequestDto instanceof RecurringWalkDto){
      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecurringWalkDto) walkRequestDto);
      walkRequestDtoFromRepo = recurringWalkMapper.toRecurringWalkDto((RecurringWalkReq) walkRequestRepo.save(walkRequest));
    }
    else
      throw new IllegalArgumentException("Invalid type of request");
    return walkRequestDtoFromRepo;
  }


}
