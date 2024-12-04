package com.theWalkingDogsApp.demo.controller;

import com.theWalkingDogsApp.demo.dto.request.walkRequest.PetReq;
import com.theWalkingDogsApp.demo.dto.response.walkRequest.PetRes;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.service.PetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
@RequestMapping("${backend.api.base-path}/pets")
public class PetsController {
    private final PetService service;

    @GetMapping
    public ResponseEntity<List<PetRes>> getAllPets(Principal principal){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.getAllPets(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetRes> getPetById(Principal principal, @PathVariable Integer id){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.getPetById(user, id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<PetRes> createPet(Principal principal, @RequestBody @Validated PetReq req){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.createPet(user,req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetRes> updatePet(Principal principal, @PathVariable Integer id, @RequestBody @Validated PetReq req){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(service.updatePet(user,id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(Principal principal, @PathVariable Integer id){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        service.deletePetById(user, id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
