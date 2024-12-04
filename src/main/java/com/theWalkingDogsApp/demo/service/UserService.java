package com.theWalkingDogsApp.demo.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.theWalkingDogsApp.demo.dto.request.user.UserProfileReq;
import com.theWalkingDogsApp.demo.model.dogOwner.DogOwner;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.user.Role;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import com.theWalkingDogsApp.demo.repository.UserRepository;
import com.theWalkingDogsApp.demo.security.auth.RegisterReq;
import com.theWalkingDogsApp.demo.service.mapper.user.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final UserProfileMapper mapper;

  public User findUserByEmail(String email) {
    return  userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
  }

  public boolean userExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User createUser(RegisterReq req){
    UserProfile userProfile = UserProfile.builder()
            .firstname(req.getFirstname())
            .lastname(req.getLastname())
            .dob(req.getDob())
            .phoneNumber(req.getPhoneNumber())
            .build();

    User user = User.builder()
            .dogOwner(new DogOwner())
            .dogWalker(new DogWalker())
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .role(Role.USER)
            .profile(userProfile)
            .build();
    return userRepository.save(user);
  }

  public void deleteById(Integer id) {
    if (!userRepository.existsById(id)) {
      throw new UsernameNotFoundException("User with id " + id + " not found");
    }
    userRepository.deleteById(id);
  }


  @Transactional
  public UserProfile updateProfile(User user, UserProfileReq req) {
    UserProfile up = mapper.toEntity(req);
    up.setId(user.getProfile().getId());
    user.setProfile(up);
    user = userRepository.save(user);
    return user.getProfile();
  }

  public UserProfile getProfile(User user){
    return user.getProfile();
  }

}
