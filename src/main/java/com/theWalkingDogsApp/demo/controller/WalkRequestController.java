package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkRequestService;
import com.theWalkingDogsApp.demo.utils.OpenApiExamples;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("${backend.api.base-path}/walkRequests")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class WalkRequestController {
  private final WalkRequestService service;

  @GetMapping
  public ResponseEntity<List<WalkRequestRes>> getAllWalkRequests(Principal principal,
                                                                 @RequestParam(required = false)
                                                                 boolean asDogWalker) {
    if (asDogWalker) {
      return ResponseEntity.ok(service.getDogWalkerRequests(getUser(principal)));
    } else {
      return ResponseEntity.ok(service.getDogOwnerRequests(getUser(principal)));
    }
  }

  @PostMapping
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(examples = {
          @ExampleObject(
              name = "Recurring walk",
              value = OpenApiExamples.recurringWalkReq
          ),
          @ExampleObject(
              name = "One Time walk",
              value = OpenApiExamples.oneTimeWalkReq
          )
      })
  )
  public ResponseEntity<WalkRequestRes> addWalkRequest(Principal principal,
                                                       @RequestBody @Validated WalkRequestReq req) {
    return ResponseEntity.ok(service.add(getUser(principal), req));
  }

  @DeleteMapping("/{walkRequestId}")
  public ResponseEntity<?> deleteWalkRequest(Principal principal,
                                             @PathVariable Integer walkRequestId) {
    service.deleteWalkRequest(getUser(principal), walkRequestId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllWalkRequests(Principal principal) {
    service.deleteAllWalkRequests(getUser(principal));
    return ResponseEntity.noContent().build();
  }

  User getUser(Principal principal) {
    return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
  }
}
