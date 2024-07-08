package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.CareGiverRequestDto;
import com.theWalkingDogsApp.demo.dto.request.DogWalkerRequestDto;
import com.theWalkingDogsApp.demo.service.CareGiverService;
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
import org.springframework.web.bind.annotation.RequestParam;
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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCareGiver (@PathVariable Integer id){
    careGiverService.deleteCareGiver(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllCareGivers(){
    careGiverService.deleteAllCareGivers();
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/dogWalkerService")
  public ResponseEntity<?> setDogWalkerService(@RequestBody @Validated DogWalkerRequestDto dogWalkerRequestDto, @PathVariable Integer id){
    careGiverService.setDogWalkerService(id,dogWalkerRequestDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}/dogWalkerService")
  public ResponseEntity<?> getDogWalkerService(@PathVariable Integer id){
    return new ResponseEntity<>(careGiverService.getDogWalker(id), HttpStatus.OK);
  }

}
