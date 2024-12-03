package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.user.UserProfileReq;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import com.theWalkingDogsApp.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("${backend.api.base-path}/users")
@SecurityRequirement(name= "bearer-jwt")
public class UserController {
  private final UserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
    userService.deleteById(id);
    return ResponseEntity.ok("Deleted successfully");
  }

  @PutMapping("/profile")
  public ResponseEntity<UserProfile> updateProfile(Principal principal, @RequestBody @Validated UserProfileReq req) {
    User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    return ResponseEntity.ok(userService.updateProfile(user, req));
  }

  @GetMapping("/profile")
  public ResponseEntity<UserProfile> getProfile(Principal principal){
    User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    return ResponseEntity.ok(userService.getProfile(user));
  }

}
