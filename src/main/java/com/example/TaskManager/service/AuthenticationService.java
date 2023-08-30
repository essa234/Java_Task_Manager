package com.example.TaskManager.service;


import com.example.TaskManager.controller.AuthenticationRequest;
import com.example.TaskManager.controller.AuthenticationResponse;
import com.example.TaskManager.controller.RegisterRequest;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  @Autowired
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request){
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(User.Role.EMPLOYEE)
        .build();
    if(!repository.findByEmail(request.getEmail()).isPresent()) {
      repository.save(user);

      var jwtToken = jwtService.generateToken(user);
      return AuthenticationResponse.builder()
          .token(jwtToken)
          .build();
    }
    return AuthenticationResponse.builder()
        .token("Email already exists, please try again")
        .build();
  }
//  public AuthenticationResponse registerManager(RegisterRequest request){
//    var user = User.builder()
//        .firstname(request.getFirstname())
//        .lastname(request.getLastname())
//        .email(request.getEmail())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .build();
//    repository.save(user);
//    var jwtToken = jwtService.generateToken(user);
//    return AuthenticationResponse.builder()
//        .token(jwtToken)
//        .build();
//  }
//
//  public AuthenticationResponse registerEmployee(RegisterRequest request){
//    var user = User.builder()
//        .firstname(request.getFirstname())
//        .lastname(request.getLastname())
//        .email(request.getEmail())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .build();
//    repository.save(user);
//    var jwtToken = jwtService.generateToken(user);
//    return AuthenticationResponse.builder()
//        .token(jwtToken)
//        .build();
//  }

  public AuthenticationResponse authenticate(AuthenticationRequest request){
     authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
     var user = repository.findByEmail(request.getEmail())
         .orElseThrow();
     var jwtToken = jwtService.generateToken(user);
     return AuthenticationResponse.builder()
         .token(jwtToken)
         .build();
  }

  public User test(){
    var user =  repository.findByEmail("bob@bob.com");
    return user.isPresent() ? user.get() : null;
  }
}
