package com.theWalkingDogsApp.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForbiddenAccessException extends RuntimeException{
    private String message;
}
