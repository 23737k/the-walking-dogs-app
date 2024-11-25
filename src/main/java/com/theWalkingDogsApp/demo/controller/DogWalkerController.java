package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.CareGiverReqDto;
import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReqDto;
import com.theWalkingDogsApp.demo.dto.response.careGiver.DogWalkerRes;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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
  public ResponseEntity<DogWalkerRes> getCareGiverById(@PathVariable Integer id) {
    return ResponseEntity.ok(service.getDogWalkerById());
  }

  @PostMapping
  public ResponseEntity<?> addCareGiver(@RequestBody @Validated CareGiverReqDto careGiver) {
    return new ResponseEntity<>(careGiverService.addCareGiver(careGiver),HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCareGiver (@PathVariable Integer id){
    careGiverService.deleteCareGiver(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllCareGivers(){
    careGiverService.deleteAllCareGivers();
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/dogWalkerService")
  public ResponseEntity<?> setDogWalkerService(@RequestBody @Validated
                                               DogWalkerReqDto dogWalkerReqDto, @PathVariable Integer id){
    careGiverService.setDogWalkerService(id, dogWalkerReqDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}/dogWalkerService")
  public ResponseEntity<?> getDogWalkerService(@PathVariable Integer id){
    return new ResponseEntity<>(careGiverService.getDogWalker(id), HttpStatus.OK);
  }

  @GetMapping("/dogWalkerService")
  public ResponseEntity<?> getAllDogWalkerServices(@PathVariable Integer id){
    return new ResponseEntity<>(careGiverService.getAllDogWalkerServices(), HttpStatus.OK);
  }


}
