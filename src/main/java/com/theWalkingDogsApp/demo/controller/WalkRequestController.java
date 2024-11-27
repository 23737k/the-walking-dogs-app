package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.WalkRequestReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.WalkRequestRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkRequestService;
import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${backend.api.base-path}/dogWalkers/walkRequests")
@AllArgsConstructor
public class WalkRequestController {
  private final WalkRequestService walkRequestService;

  @GetMapping
  public ResponseEntity<List<WalkRequestRes>> getAllWalkRequests(Principal principal) {
    return ResponseEntity.ok(walkRequestService.getAll(getUser(principal)));
  }

  @PostMapping
  public ResponseEntity<WalkRequestRes> addWalkRequest(Principal principal, @RequestBody @Validated WalkRequestReq req) {
    return ResponseEntity.ok(walkRequestService.add(getUser(principal), req));
  }

  @DeleteMapping("/{walkRequestId}")
  public ResponseEntity<?> deleteWalkRequest(@PathVariable Integer walkRequestId) {
    walkRequestService.deleteWalkRequest(walkRequestId);
    return ResponseEntity.noContent().build();
  }
  @DeleteMapping
  public ResponseEntity<?> deleteAllWalkRequests(Principal principal) {
    walkRequestService.deleteAllWalkRequests(getUser(principal));
    return ResponseEntity.noContent().build();
  }

  User getUser(Principal principal){
    return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
  }

}
