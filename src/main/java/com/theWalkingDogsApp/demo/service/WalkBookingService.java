package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkBookingRes;
import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkBookingRepo;
import com.theWalkingDogsApp.demo.service.mapper.walkBooking.WalkBookingMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkBookingService {
    private final WalkBookingRepo repo;
    private final WalkBookingMapper mapper;
    private final WalkRequestService walkRequestService;

    @Transactional(readOnly = true)
    public List<WalkBookingRes> getDogWalkerBookings(User user) {
        DogWalker dogWalker = user.getDogWalker();
        return repo.findWalkBookingByDogWalker(dogWalker).stream().map(mapper::toRes).toList();
    }

    @Transactional(readOnly = true)
    public List<WalkBookingRes> getDogOwnerBookings(User user) {
        DogOwner dogOwner = user.getDogOwner();
        return repo.findWalkBookingByDogOwner(dogOwner).stream().map(mapper::toRes).toList();

    }

    @Transactional
    public WalkBookingRes addWalkBooking(User user, WalkBookingReq req) {
        WalkRequest walkRequest = walkRequestService.findById(req.getWalkRequestId());
        WalkBooking walkBooking = walkRequest.createBooking();
        walkRequestService.deleteWalkRequest(user, walkRequest.getId());
        return mapper.toRes(repo.save(walkBooking));
    }


//    public WalkBooking getWalkBookingById(Integer id){
//        return repo.findById(id).orElseThrow(()->new EntityNotFoundException("Walk Booking with ID "+ id + " does not exist" ));
//    }

}
