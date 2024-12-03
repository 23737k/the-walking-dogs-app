package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
