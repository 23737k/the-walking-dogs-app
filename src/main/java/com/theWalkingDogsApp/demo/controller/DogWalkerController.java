package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerRes;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
