package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReqDto;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkBookingRepo;
import com.theWalkingDogsApp.demo.repository.WalkRequestRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkBookingService {
    private final WalkBookingRepo walkBookingRepo;
    private final WalkRequestRepo walkRequestRepo;
    private final CareGiverService careGiverService;

    public List<WalkBooking> getAllWalkBookings(Integer careGiverId){
        CareGiver careGiver = careGiverService.validate(careGiverId);
        return careGiver.getDogWalker().getWalkBookings();
    }

    public WalkBooking getWalkBookingById(Integer id){
        return walkBookingRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Walk Booking with ID "+ id + " does not exist" ));
    }


    @Transactional
    public WalkBooking addWalkBooking(Integer careGiverId, WalkBookingReqDto walkBookingReqDto) {
        CareGiver careGiver = careGiverService.validate(careGiverId);
        DogWalker dogWalker = careGiver.getDogWalker();
        Integer walkRequestId = walkBookingReqDto.getWalkRequestId();
        WalkRequest walkRequest = walkRequestRepo.findById(walkRequestId)
            .orElseThrow(() -> new EntityNotFoundException("Walk Request " + walkRequestId + " does not exist"));

        WalkBooking walkBooking = walkBookingRepo.save(walkRequest.createBooking());
        dogWalker.getWalkRequests().remove(walkRequest);
        dogWalker.getWalkBookings().add(walkBooking);
        careGiverService.saveCareGiver(careGiver);
        return walkBookingRepo.save(walkBooking);
    }



}
