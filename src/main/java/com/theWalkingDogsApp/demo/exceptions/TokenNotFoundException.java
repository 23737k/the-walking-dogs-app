package com.theWalkingDogsApp.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenNotFoundException extends RuntimeException {
  private String message;
}
