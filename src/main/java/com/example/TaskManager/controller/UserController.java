package com.example.TaskManager.controller;

import com.example.TaskManager.models.User;
import com.example.TaskManager.service.UserService;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping()
  public ResponseEntity<User> getUser(@RequestParam String userEmail,
                                      @RequestBody AuthenticationRequest request
  ){
    return ResponseEntity.ok(userService.getUser(userEmail, request));
  }

  @DeleteMapping()
  public ResponseEntity<UserResponse> deleteUser(@RequestParam Long userId,
                                                 @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(userService.deleteUser(userId, request));
  }

  @PatchMapping()
  public ResponseEntity<UserResponse> updateUserDetails(@RequestParam Long userId,
                                                @RequestParam(required = false) String password,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(required = false) String firstname,
                                                @RequestParam(required = false) String lastname,
                                                @RequestParam(required = false) String role,
                                                @RequestBody AuthenticationRequest request){
    return ResponseEntity.ok(userService.updateUser(userId, password, email, firstname, lastname, role, request));
  }
}
