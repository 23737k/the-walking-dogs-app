package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${backend.api.base-path}/dogWalkers")
@RequiredArgsConstructor
public class DogWalkerController {
  private final DogWalkerService service;

  @GetMapping
  public ResponseEntity<List<DogWalkerRes>> getDogWalkers() {
    return ResponseEntity.ok(service.getDogWalkers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DogWalkerRes> getDogWalkerById(@PathVariable Integer id) {
    return ResponseEntity.ok(service.getDogWalkerById(id));
  }

  //TODO
  @PutMapping()
  public ResponseEntity<DogWalkerRes> updateDogWalker(@RequestBody @Validated DogWalkerReq dogWalkerReq) {
    return ResponseEntity.ok(service.updateDogWalker(dogWalkerReq));
  }

}
