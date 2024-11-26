//package com.theWalkingDogsApp.demo.service;
//
//import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReqDto;
//import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
//import com.theWalkingDogsApp.demo.model.user.User;
//import com.theWalkingDogsApp.demo.model.walkRequest.OneTimeWalk;
//import com.theWalkingDogsApp.demo.model.walkRequest.RecurringWalk;
//import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
//import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
//import com.theWalkingDogsApp.demo.service.mapper.walkRequest.OneTimeWalkMapper;
//import com.theWalkingDogsApp.demo.service.mapper.walkRequest.RecurringWalkMapper;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class WalkRequestService {
//  private final WalkRequestRepo walkRequestRepo;
//  private final OneTimeWalkMapper oneTimeWalkMapper;
//  private final RecurringWalkMapper recurringWalkMapper;
//  private final CareGiverRepo careGiverRepo;
//
//
//  public List<WalkRequestRes> getAll(User user) {
//
//    List<WalkRequest> walkRequests = walkRequestRepo.findAllByDogWalkerId(5);
//    List<WalkRequestRes> dtos = new ArrayList<>();
//
//    for (WalkRequest walkRequest : walkRequests) {
//      if (walkRequest instanceof RecurringWalk recurringWalk) {
//        WalkRequestRes dto = recurringWalkMapper.toRecurringWalkResDto(recurringWalk);
//        dtos.add(dto);
//      } else if (walkRequest instanceof OneTimeWalk oneTimeWalk) {
//        WalkRequestRes dto = oneTimeWalkMapper.toOneTimeWalkResDto(oneTimeWalk);
//        dtos.add(dto);
//      }
//    }
//
//    return dtos;
//  }
//
//  public WalkRequestRes add(Integer careGiverId, WalkRequestReqDto walkRequestReqDto) {
//
//    WalkRequestRes walkRequestRes;
//    WalkRequest walkRequest;
////    CareGiver careGiver = careGiverRepo.findById(careGiverId).orElseThrow(()->new EntityNotFoundException("CareGiver not found"));
////    DogWalker dogWalker = careGiver.getDogWalker();
////
////    if(walkRequestReqDto instanceof OTWalkReqDto){
////      walkRequest = oneTimeWalkMapper.toOneTimeWalkReq((OTWalkReqDto) walkRequestReqDto);
////      walkRequest.setDogWalker(dogWalker);
////      walkRequestRes = oneTimeWalkMapper.toOneTimeWalkResDto((OneTimeWalk) walkRequestRepo.save(walkRequest));
////      careGiverRepo.save(careGiver);
////    }
////
////    else if(walkRequestReqDto instanceof RecWalkReqDto){
////      walkRequest = recurringWalkMapper.toRecurringWalkReq((RecWalkReqDto) walkRequestReqDto);
////      walkRequest.setDogWalker(dogWalker);
////      walkRequestRes = recurringWalkMapper.toRecurringWalkResDto((RecurringWalk) walkRequestRepo.save(walkRequest));
////      careGiverRepo.save(careGiver);
////    }
////
////    else
////      throw new IllegalArgumentException("Invalid type of request");
////    return walkRequestRes;
//    return null;
//  }
//
//  public void deleteWalkRequest(Integer id) {
//    validate(id);
//    walkRequestRepo.deleteById(id);
//  }
//
//  public WalkRequest validate(Integer id){
//    return walkRequestRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("WalkRequest with id " + id + " not found"));
//  }
//
//  @Transactional
//  public void deleteAllWalkRequests(Integer dogWalkerId){
//    walkRequestRepo.deleteAllByDogWalkerId(dogWalkerId);
//  }
//}
