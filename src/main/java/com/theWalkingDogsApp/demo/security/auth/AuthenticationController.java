package com.theWalkingDogsApp.demo.security.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${backend.api.base-path}/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<AuthRes> login(@RequestBody @Valid AuthReq authReq) {
    return ResponseEntity.ok(authenticationService.authenticate(authReq));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthRes> register(@RequestBody @Valid RegisterReq registerReq) {
    return ResponseEntity.ok(authenticationService.register(registerReq));
  }

}
