package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkBookingRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkBookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("${backend.api.base-path}/walkBookings")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class WalkBookingController {
    private final WalkBookingService service;

    @GetMapping
    public ResponseEntity<List<WalkBookingRes>> getAllBookings(@RequestParam(required = false) boolean asDogWalker, Principal principal){
        if(asDogWalker){
            return ResponseEntity.ok(service.getDogWalkerBookings(getUser(principal)));
        }
        else
            return ResponseEntity.ok(service.getDogOwnerBookings(getUser(principal)));
    }

    @PostMapping
    public ResponseEntity<WalkBookingRes> addWalkBooking(@RequestBody WalkBookingReq walkBookingReq, Principal principal){
        return ResponseEntity.ok(service.addWalkBooking(getUser(principal), walkBookingReq));
    }

//    @GetMapping("/{walkBookingId}")
//    public ResponseEntity<?> getWalkBookingById(@PathVariable Integer walkBookingId){
//        return ResponseEntity.ok(service.getWalkBookingById(walkBookingId));
//    }

    private User getUser(Principal principal){
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }


}
