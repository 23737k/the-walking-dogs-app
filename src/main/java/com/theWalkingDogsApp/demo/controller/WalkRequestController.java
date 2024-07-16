package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.service.CareGiverService;
import com.theWalkingDogsApp.demo.service.WalkRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/careGivers/{careGiverId}/dogWalkerService/walkRequests")
@AllArgsConstructor
public class WalkRequestController {
  private final WalkRequestService walkRequestService;
  private final CareGiverService careGiverService;

  @PostMapping
  public ResponseEntity<?> addWalkRequest(@PathVariable Integer careGiverId, @RequestBody @Validated WalkRequestReqDto walkRequest) {
    return ResponseEntity.ok(walkRequestService.add(careGiverId, walkRequest));
  }

  @GetMapping
  public ResponseEntity<?> getAllWalkRequests(@PathVariable Integer careGiverId) {
    return ResponseEntity.ok(walkRequestService.getAll(careGiverService.validate(careGiverId).getDogWalker().getId()));
  }

  @DeleteMapping("/{walkRequestId}")
  public ResponseEntity<?> deleteWalkRequest(@PathVariable Integer careGiverId, @PathVariable Integer walkRequestId) {
    careGiverService.validate(careGiverId);
    walkRequestService.deleteWalkRequest(walkRequestId);
    return ResponseEntity.noContent().build();
  }
  @DeleteMapping
  public ResponseEntity<?> deleteAllWalkRequests(@PathVariable Integer careGiverId) {
    Integer dogWalkerId = careGiverService.validate(careGiverId).getDogWalker().getId();
    walkRequestService.deleteAllWalkRequests(dogWalkerId);
    return ResponseEntity.noContent().build();
  }

}
