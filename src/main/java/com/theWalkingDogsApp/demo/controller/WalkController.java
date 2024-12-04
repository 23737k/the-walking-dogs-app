package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walk.WalkReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkBookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
@RequestMapping("${backend.api.base-path}/walkBookings/{walkBookingId}/walks")
public class WalkController {
    private final WalkBookingService service;

    @GetMapping()
    public ResponseEntity<List<WalkRes>> getWalks(Principal principal, @PathVariable Integer walkBookingId){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.getWalks(user,walkBookingId));
    }

    @GetMapping("/{walkId}")
    public ResponseEntity<WalkRes> getWalkById(Principal principal, @PathVariable Integer walkBookingId, @PathVariable Integer walkId){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.getWalkById(user, walkBookingId,walkId));
    }

    @PutMapping("/{walkId}")
    public ResponseEntity<WalkRes> updateWalk(Principal principal, @PathVariable Integer walkBookingId, @PathVariable Integer walkId, @RequestBody @Validated WalkReq req){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.updateWalk(user,walkBookingId,walkId,req));
    }



}
