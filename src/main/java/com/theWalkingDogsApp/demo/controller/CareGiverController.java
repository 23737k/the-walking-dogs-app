package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.CareGiverRequestDto;
import com.theWalkingDogsApp.demo.service.CareGiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/careGivers")
@RequiredArgsConstructor
public class CareGiverController {
  private final CareGiverService careGiverService;

  @GetMapping
  public ResponseEntity<?> getCareGivers() {
    return new ResponseEntity<>(careGiverService.getCareGivers(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addCareGiver(@RequestBody CareGiverRequestDto careGiver) {
    return new ResponseEntity<>(careGiverService.addCareGiver(careGiver),HttpStatus.CREATED);
  }
}
