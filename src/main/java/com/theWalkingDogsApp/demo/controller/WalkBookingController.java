package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkBookingRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.WalkBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Get all the walk bookings for the current user")
    public ResponseEntity<List<WalkBookingRes>> getAllBookings(
        @RequestParam(required = false) @Parameter(description = "If you choose 'asDogWalker' as true, you will receive the walk bookings made by others to you. \n\nThe default is value is false, and you will get the walk bookings that you have made to a Dog walker ")
        boolean asDogWalker,
        Principal principal){
        if(asDogWalker){
            return ResponseEntity.ok(service.getDogWalkerBookings(getUser(principal)));
        }
        else
            return ResponseEntity.ok(service.getDogOwnerBookings(getUser(principal)));
    }

    @PostMapping
    @Operation(summary = "Creates a new walk booking", description = "Creates a new walk booking along with its associated walks based on the specified walk request")
    public ResponseEntity<WalkBookingRes> addWalkBooking(@RequestBody WalkBookingReq walkBookingReq, Principal principal){
        return ResponseEntity.ok(service.addWalkBooking(getUser(principal), walkBookingReq));
    }

    private User getUser(Principal principal){
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }


}
