package com.theWalkingDogsApp.demo.security.jwt;


import com.theWalkingDogsApp.demo.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Service
public class JwtAuthFilter extends OncePerRequestFilter {
  private final String[] whiteListedUrls;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    try{
      if(shouldNotFilter(request)){
        filterChain.doFilter(request, response);
        return;
      }
      String token = getToken(request);
      if(token == null)
        throw new ServletException("Token not found");

      UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
      boolean tokenExists = tokenService.exists(token);

      if(jwtService.isValid(userDetails,token) && tokenExists && SecurityContextHolder.getContext().getAuthentication() == null){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    catch(Exception e){
      sendErrorResponse(response, 401);
    }
    filterChain.doFilter(request, response);
  }


  public String getToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if(header != null && header.startsWith("Bearer "))
      return header.substring(7);
    return null;
  }

  public void sendErrorResponse(HttpServletResponse response, int errorCode) throws IOException {
    response.getWriter().write("Access denied");
    response.setStatus(errorCode);
    response.setContentType("application/json");
  }

  public boolean shouldNotFilter(HttpServletRequest request) {
    return Arrays.stream(whiteListedUrls).anyMatch(request.getRequestURI()::startsWith);
  }


}
