package com.example.TaskManager.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.TaskManager.config.UserSeeder;
import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
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
  private UserSeeder userSeeder;

  @BeforeEach
  void setup(){
    userSeeder.clearUserTable();
  }

  @Test
  void itShouldGetAUserById() {
    User user = User.builder()
        .userId(1L)
        .email("")
        .password("")
        .firstname("")
        .lastname("")
        .role(User.Role.EMPLOYEE)
        .build();

    Task task = Task.builder()
        .taskId(1L)
        .title("test task")
        .description("this is a test task")
        .user(user)
        .build();

    user.setTasks(Set.of(task));

    userService.save(user);

    User foundUser = userController.getUser(1L).getBody();
    assertThat(foundUser.getUserId()).isEqualTo(1L);
  }

  @Test
  void itCanDeleteAUserById() {
    User user = User.builder()
        .userId(1L)
        .email("")
        .password("")
        .firstname("")
        .lastname("")
        .role(User.Role.EMPLOYEE)
        .build();

    Task task = Task.builder()
        .taskId(1L)
        .title("test task")
        .description("this is a test task")
        .user(user)
        .build();

    user.setTasks(Set.of(task));

    userService.save(user);

    User deletedUser = userController.deleteUser(1L).getBody();
    assertThat(deletedUser.getUserId()).isEqualTo(1L);
  }

  @Test
  void itCanUpdateAUser() {
    User user = User.builder()
        .userId(1L)
        .email("")
        .password("")
        .firstname("")
        .lastname("")
        .role(User.Role.EMPLOYEE)
        .build();

    Task task = Task.builder()
        .taskId(1L)
        .title("test task")
        .description("this is a test task")
        .user(user)
        .build();

    user.setTasks(Set.of(task));

    userService.save(user);

    User updatedUser = userController.updateUserDetails(1L, null, "newemail@test.com", null, null, null).getBody();
    assertThat(updatedUser.getUserId()).isEqualTo(1L);
    assertThat(updatedUser.getEmail()).isEqualTo("newemail@test.com");
  }

}
