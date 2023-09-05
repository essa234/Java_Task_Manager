package com.example.TaskManager.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.TaskManager.config.UserSeeder;
import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.service.TaskService;
import com.example.TaskManager.service.UserService;
import jakarta.persistence.Access;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {

  @Autowired
  UserController userController;

  @Autowired
  UserService userService;

  @Autowired
  UserSeeder userSeeder;


  @BeforeEach
  void setup(){
    userSeeder.seedUsersTable();
  }

  @Test
  void itShouldGetAUserById() {
    User user = User.builder()
        .email("email@email.com")
        .password("")
        .firstname("")
        .lastname("")
        .role(String.valueOf(User.Role.EMPLOYEE))
        .build();

    user.setTasks(Set.of());

    userService.save(user);

    User foundUser = userController.getUser("email@email.com",
        AuthenticationRequest.builder()
            .email("admin@admin.com")
            .password("admin")
            .build()).getBody();
    assertThat(foundUser.getEmail()).isEqualTo("email@email.com");
  }

  @Test
  void itCanDeleteAUserById() {
    User user = User.builder()
        .userId(1L)
        .email("email@email.com")
        .password("")
        .firstname("")
        .lastname("")
        .role(String.valueOf(User.Role.EMPLOYEE))
        .build();

    user.setTasks(Set.of());

    userService.save(user);

    UserResponse deletedUser = userController.deleteUser(1L, AuthenticationRequest.builder()
        .email("admin@admin.com")
        .password("admin")
        .build()).getBody();
    assertThat(deletedUser.getMessage()).isEqualTo("User deleted successfully");
  }

  @Test
  void itCanUpdateAUser() {
    User user = User.builder()
        .userId(1L)
        .email("")
        .password("")
        .firstname("")
        .lastname("")
        .role(String.valueOf(User.Role.EMPLOYEE))
        .build();

    user.setTasks(Set.of());

    userService.save(user);

    UserResponse updatedUser = userController.updateUserDetails(1L,
        null,
        "newemail@test.com",
        null,
        null,
        null,
        AuthenticationRequest.builder()
            .email("admin@admin.com")
            .password("admin")
            .build()).getBody();

    assertThat(updatedUser.getMessage()).isEqualTo("User updated successfully");
  }

}
