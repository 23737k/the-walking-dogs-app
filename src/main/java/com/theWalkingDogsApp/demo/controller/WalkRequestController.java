//package com.theWalkingDogsApp.demo.controller;
//
//import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReqDto;
//import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
//import com.theWalkingDogsApp.demo.model.user.User;
//import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
//import com.theWalkingDogsApp.demo.service.WalkRequestService;
//import java.security.Principal;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("${backend.api.base-path}/dogwalkers/me/walkRequests")
//@AllArgsConstructor
//public class WalkRequestController {
//  private final WalkRequestService walkRequestService;
//
//  @GetMapping
//  public ResponseEntity<List<WalkRequestRes>> getAllWalkRequests(Principal principal) {
//    User user = (User) principal;
//    return ResponseEntity.ok(walkRequestService.getAll(user));
//  }
//
//
//  @PostMapping
//  public ResponseEntity<?> addWalkRequest(@PathVariable Integer careGiverId, @RequestBody @Validated WalkRequestReqDto walkRequest) {
//    return ResponseEntity.ok(walkRequestService.add(careGiverId, walkRequest));
//  }
//
//  @DeleteMapping("/{walkRequestId}")
//  public ResponseEntity<?> deleteWalkRequest(@PathVariable Integer careGiverId, @PathVariable Integer walkRequestId) {
//    walkRequestService.validate(careGiverId);
//    walkRequestService.deleteWalkRequest(walkRequestId);
//    return ResponseEntity.noContent().build();
//  }
//  @DeleteMapping
//  public ResponseEntity<?> deleteAllWalkRequests(@PathVariable Integer careGiverId) {
//    Integer dogWalkerId = walkRequestService.validate(careGiverId).getDogWalker().getId();
//    walkRequestService.deleteAllWalkRequests(dogWalkerId);
//    return ResponseEntity.noContent().build();
//  }
//
//}
