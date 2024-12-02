package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerFilter;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("${backend.api.base-path}/dogWalkers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class DogWalkerController {
  private final DogWalkerService service;

  @GetMapping
  public ResponseEntity<List<DogWalkerRes>> getDogWalkers(@ParameterObject @ModelAttribute @Validated DogWalkerFilter filter) {
    return ResponseEntity.ok(service.getDogWalkers(filter));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DogWalkerRes> getDogWalkerById(@PathVariable Integer id) {
    return ResponseEntity.ok(service.getDogWalkerById(id));
  }

  @GetMapping("/me")
  public ResponseEntity<DogWalkerRes> getDogWalker(Principal principal){
    User user =(User)( ((UsernamePasswordAuthenticationToken)principal).getPrincipal());
    return ResponseEntity.ok(service.getDogWalkerById(user.getDogWalker().getId()));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteDogWalker(@PathVariable Integer id){
    service.deleteDogWalkerById(id);
    return ResponseEntity.ok("Deleted successfully");
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping
  public ResponseEntity<String> deleteAllDogWalkers(){
    service.deleteAll();
    return ResponseEntity.ok("All deleted successfully");
  }


  @PutMapping("/me")
  public ResponseEntity<DogWalkerRes> updateDogWalker(Principal principal, @RequestBody @Validated DogWalkerReq dogWalkerReq) {
    User user =(User)( ((UsernamePasswordAuthenticationToken)principal).getPrincipal());
    return ResponseEntity.ok(service.updateDogWalker(user.getDogWalker().getId(),dogWalkerReq));
  }

}
