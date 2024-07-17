package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReqDto;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.service.WalkBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/careGivers/{careGiverId}/dogWalkerService/walkBookings")
@RequiredArgsConstructor
public class WalkBookingController {
    private final WalkBookingService walkBookingService;

    @GetMapping
    public ResponseEntity<?> getAllBookings(@PathVariable Integer careGiverId){
        return ResponseEntity.ok(walkBookingService.getAllWalkBookings(careGiverId));
    }

    @GetMapping("/{walkBookingId}")
    public ResponseEntity<?> getWalkBookingById(@PathVariable Integer walkBookingId){
        return ResponseEntity.ok(walkBookingService.getWalkBookingById(walkBookingId));
    }

    @PostMapping
    public ResponseEntity<?> addWalkBooking(@RequestBody WalkBookingReqDto walkBookingReqDto, @PathVariable Integer careGiverId){
        return ResponseEntity.ok(walkBookingService.addWalkBooking(careGiverId, walkBookingReqDto));
    }


}
