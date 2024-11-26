package com.theWalkingDogsApp.demo.security.token;

import com.theWalkingDogsApp.demo.exceptions.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final TokenRepository tokenRepository;

  public void revokeToken(String token) {
    tokenRepository.findByToken(token).ifPresentOrElse(tokenRepository::delete,() -> {throw new TokenNotFoundException("Token not found");});
  }

  public boolean exists(String token) {
    return tokenRepository.findByToken(token).isPresent();
  }

  public Token save(Token token) {
    return tokenRepository.save(token);
  }
}
