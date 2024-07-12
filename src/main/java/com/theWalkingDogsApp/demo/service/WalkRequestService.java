package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.OTWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkSubmission.RecWalkReqDto;
import com.theWalkingDogsApp.demo.dto.request.walkSubmission.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
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

  public WalkRequestReqDto add(WalkRequestReqDto walkRequestReqDto) {

    WalkRequest walkRequest;
    WalkRequestReqDto walkRequestReqDtoFromRepo;

    if(walkRequestReqDto instanceof OTWalkReqDto){
      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OTWalkReqDto) walkRequestReqDto);
      walkRequestReqDtoFromRepo = oneTimeWalkMapper.toOneTimeWalkReqDto((OneTimeWalk) walkRequestRepo.save(walkRequest));
    }
    else if(walkRequestReqDto instanceof RecWalkReqDto){
      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecWalkReqDto) walkRequestReqDto);
      walkRequestReqDtoFromRepo = recurringWalkMapper.toRecurringWalkDto((RecurringWalk) walkRequestRepo.save(walkRequest));
    }
    else
      throw new IllegalArgumentException("Invalid type of request");
    return walkRequestReqDtoFromRepo;
  }


}
