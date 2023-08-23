package com.example.TaskManager.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.TaskManager.config.UserSeeder;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.service.AuthenticationService;
import com.example.TaskManager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthControllerTest {

  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthenticationService authenticationService;
  @Autowired
  AuthController authController;

  private UserSeeder userSeeder;

  @BeforeEach
  void setup(){
    userSeeder.clearUserTable();
  }

  @Test
  void itCanRegisterAUser(){
    RegisterRequest request = new RegisterRequest("firstname", "lastname", "email@email.com", "password");
    authController.register(request);

    User foundUser = userRepository.findByEmail("email@email.com").get();

    assertThat(foundUser.getUserId()).isEqualTo(1L);
  }
}
