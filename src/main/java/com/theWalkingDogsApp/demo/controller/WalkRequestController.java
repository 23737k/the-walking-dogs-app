package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkRequestService;
import com.theWalkingDogsApp.demo.utils.OpenApiExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
  @Operation(summary = "Get all the walk request for the current user")
  public ResponseEntity<List<WalkRequestRes>> getAllWalkRequests(Principal principal,
                                                                 @RequestParam(required = false)
                                                                     @Parameter(description = "If you choose 'asDogWalker' as true, you will receive the walk requests made by others to you. \n\nThe default is value is false, and you will get the walk requests that you have made to a Dog walker ")
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
  @Operation(summary = "Create a walk request", description = "There are two types of walk requests: one-time and recurring. \n\n\n\n" +
      "one-time: this type of walk request is suitable when you need a dog walk service to occur only once. You specify the dates and times you need for the walk. For each day, you can specify several time slots." +
      "\n\n\n\nrecurring: this type of walk is suitable when you need one or more walks to repeat periodically. Unlike one time walks, you have to specify the days of the week and times you need for the walk. Additionally, you have to specify both the start and end dates for the service." +
      "These dates indicates the time period during which the walk service will be active. Each week within the service period, the walks will repeat on the specified days")
  public ResponseEntity<WalkRequestRes> addWalkRequest(Principal principal,
                                                       @RequestBody @Validated WalkRequestReq req) {
    return ResponseEntity.ok(service.add(getUser(principal), req));
  }

  @DeleteMapping("/{walkRequestId}")
  @Operation(summary = "Delete a walk request by its id")
  public ResponseEntity<?> deleteWalkRequest(Principal principal,
                                             @PathVariable Integer walkRequestId) {
    service.deleteWalkRequest(getUser(principal), walkRequestId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  @Operation(summary = "Deletes all walk requests")
  public ResponseEntity<?> deleteAllWalkRequests(Principal principal) {
    service.deleteAllWalkRequests(getUser(principal));
    return ResponseEntity.noContent().build();
  }

  User getUser(Principal principal) {
    return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
  }
}
