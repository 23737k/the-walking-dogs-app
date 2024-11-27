package com.theWalkingDogsApp.demo.security.auth;

import com.theWalkingDogsApp.demo.exceptions.UserAlreadyExistsException;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import com.theWalkingDogsApp.demo.security.jwt.JwtService;
import com.theWalkingDogsApp.demo.security.token.Token;
import com.theWalkingDogsApp.demo.security.token.TokenService;
import com.theWalkingDogsApp.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final JwtService jwtService;
  private final TokenService tokenService;
  private final AuthenticationProvider authenticationProvider;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public AuthRes authenticate(AuthReq authReq) {
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authReq.getEmail(),authReq.getPassword());
    UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(authentication).getPrincipal();

    Token token = new Token(jwtService.getAccessToken(userDetails));
    tokenService.save(token);
    return new AuthRes(token.getToken());
  }

  @Transactional
  public AuthRes register(RegisterReq req) {
    if(userService.userExists(req.getEmail()))
      throw new UserAlreadyExistsException("This email is already registered");

    UserProfile userProfile = UserProfile.builder()
        .firstname(req.getFirstname())
        .lastname(req.getLastname())
        .dob(req.getDob())
        .phoneNumber(req.getPhoneNumber())
        .build();

    User user = new User(req.getEmail(), passwordEncoder.encode(req.getPassword()), userProfile);
    user = userService.save(user);

    Token token = new Token(jwtService.getAccessToken(user));
    tokenService.save(token);
    return new AuthRes(token.getToken());

  }
}
