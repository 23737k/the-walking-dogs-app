package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReqDto;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkBookingRepo;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkBookingService {
    private final WalkBookingRepo walkBookingRepo;
    private final WalkRequestRepo walkRequestRepo;

    public List<WalkBooking> getAllWalkBookings(){
        return walkBookingRepo.findAll();
    }

    public WalkBooking getWalkBookingById(Integer id){
        return walkBookingRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Walk Booking with ID "+ id + " does not exist" ));
    }

    public WalkBooking addWalkBooking(WalkBookingReqDto walkBookingReqDto){
        WalkRequest w walkRequestRepo.findById(walkBookingReqDto.getWalkRequestId()).orElseThrow(()-> new MethodArgumentNotValidException("WalkRequest with ID"));

    }
}
