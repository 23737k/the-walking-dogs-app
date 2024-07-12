package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkSubmission.WalkRequestReqDto;
import com.theWalkingDogsApp.demo.service.WalkRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/walkRequests")
@AllArgsConstructor
public class WalkSubmissionController {
  private final WalkRequestService walkRequestService;

  @PostMapping
  public ResponseEntity<?> addWalkRequest(@RequestBody WalkRequestReqDto walkRequest) {
    return ResponseEntity.ok(walkRequestService.add(walkRequest));
  }

  @GetMapping
  public ResponseEntity<?> getAllWalkRequests() {
    return ResponseEntity.ok(walkRequestService.getAll());
  }

}