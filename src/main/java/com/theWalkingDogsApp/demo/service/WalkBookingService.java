package com.theWalkingDogsApp.demo.service;

import com.theWalkingDogsApp.demo.dto.request.walk.WalkReq;
import com.theWalkingDogsApp.demo.dto.request.walkBooking.WalkBookingReq;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkBookingRes;
import com.theWalkingDogsApp.demo.dto.response.walkBooking.WalkRes;
import com.theWalkingDogsApp.demo.exceptions.ForbiddenAccessException;
import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.walkBooking.Walk;
import com.theWalkingDogsApp.demo.model.walkBooking.WalkBooking;
import com.theWalkingDogsApp.demo.model.walkRequest.WalkRequest;
import com.theWalkingDogsApp.demo.repository.WalkBookingRepo;
import com.theWalkingDogsApp.demo.service.mapper.WalkMapper;
import com.theWalkingDogsApp.demo.service.mapper.walkBooking.WalkBookingMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkBookingService {
    private final WalkBookingRepo repo;
    private final WalkBookingMapper mapper;
    private final WalkMapper walkMapper;
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


    public List<WalkRes> getWalks(User user, Integer walkBookingId){
        WalkBooking walkBooking = repo.findById(walkBookingId).orElseThrow(()-> new EntityNotFoundException("WalkBooking with id " + walkBookingId + " not found"));
        if(!repo.findWalkBookingByDogWalker(user.getDogWalker()).contains(walkBooking) && !repo.findWalkBookingByDogOwner(user.getDogOwner()).contains(walkBooking))
            throw new ForbiddenAccessException("You don't have permission to access this resource");
        return walkBooking.getWalks().stream().map(walkMapper::toRes).toList();
    }

    public WalkRes updateWalk(User user, Integer walkBookingId, Integer walkId, WalkReq req) {
        WalkBooking walkBooking = repo.findById(walkBookingId).orElseThrow(()-> new EntityNotFoundException("WalkBooking with id " + walkBookingId + " not found"));
        if(!repo.findWalkBookingByDogWalker(user.getDogWalker()).contains(walkBooking) && !repo.findWalkBookingByDogOwner(user.getDogOwner()).contains(walkBooking))
            throw new ForbiddenAccessException("You don't have permission to access this resource");
        Walk updatedWalk = walkMapper.toEntity(req);
        updatedWalk.setId(walkId);
        Walk currentWalk = walkBooking.getWalks().stream().filter(w-> w.getId().equals(walkId))
                .findAny().orElseThrow(()-> new EntityNotFoundException("Walk with id: " + walkId + " not found"));

        walkBooking.getWalks().remove(currentWalk);
        walkBooking.getWalks().add(updatedWalk);
        repo.save(walkBooking);
        return walkMapper.toRes(updatedWalk);
    }

    public WalkRes getWalkById(User user, Integer walkBookingId, Integer walkId) {
        WalkBooking walkBooking = repo.findById(walkBookingId).orElseThrow(()-> new EntityNotFoundException("WalkBooking with id " + walkBookingId + " not found"));
        if(!repo.findWalkBookingByDogWalker(user.getDogWalker()).contains(walkBooking) && !repo.findWalkBookingByDogOwner(user.getDogOwner()).contains(walkBooking))
            throw new ForbiddenAccessException("You don't have permission to access this resource");
        Walk walk = walkBooking.getWalks().stream().filter(w-> w.getId().equals(walkId))
                .findAny().orElseThrow(()-> new EntityNotFoundException("Walk with id: " + walkId + " not found"));
        return walkMapper.toRes(walk);
    }
}
