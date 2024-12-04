package com.theWalkingDogsApp.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${backend.api.base-path}/keepAlive")
public class KeepAliveController {

  @GetMapping
  public ResponseEntity<String> keepAlive() {
    return ResponseEntity.ok("I'm alive!");
  }
}
