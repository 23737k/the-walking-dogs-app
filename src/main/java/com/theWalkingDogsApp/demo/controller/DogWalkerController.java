package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerFilter;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${backend.api.base-path}/dogWalkers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class DogWalkerController {
  private final DogWalkerService service;

  @GetMapping
  @Operation(summary = "Get all DogWalkers")
  public ResponseEntity<List<DogWalkerRes>> getDogWalkers(@ParameterObject @ModelAttribute @Validated DogWalkerFilter filter) {
    return ResponseEntity.ok(service.getDogWalkers(filter));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a DogWalker by Id")
  public ResponseEntity<DogWalkerRes> getDogWalkerById(@PathVariable Integer id) {
    return ResponseEntity.ok(service.getDogWalkerById(id));
  }

  @GetMapping("/me")
  @Operation(summary = "Get the DogWalker profile of the current user")
  public ResponseEntity<DogWalkerRes> getDogWalker(Principal principal){
    User user =(User)( ((UsernamePasswordAuthenticationToken)principal).getPrincipal());
    return ResponseEntity.ok(service.getDogWalkerById(user.getDogWalker().getId()));
  }



  @PutMapping("/me")
  @Operation(summary = "Modify the current user's DogWalker profile")
  public ResponseEntity<DogWalkerRes> updateDogWalker(Principal principal, @RequestBody @Validated DogWalkerReq dogWalkerReq) {
    User user =(User)( ((UsernamePasswordAuthenticationToken)principal).getPrincipal());
    return ResponseEntity.ok(service.updateDogWalker(user.getDogWalker().getId(),dogWalkerReq));
  }

}
