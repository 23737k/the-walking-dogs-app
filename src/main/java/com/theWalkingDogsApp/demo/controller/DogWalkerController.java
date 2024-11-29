package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.careGiver.DogWalkerReq;
import com.theWalkingDogsApp.demo.dto.response.dogWalker.DogWalkerRes;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerFilter;
import com.theWalkingDogsApp.demo.repository.specification.DogWalkerSpec;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<DogWalkerRes>> getDogWalkers(@ParameterObject @ModelAttribute @Validated DogWalkerFilter filter) {
    return ResponseEntity.ok(service.getDogWalkers(filter));
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
