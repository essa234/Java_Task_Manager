package com.example.TaskManager.controller;

import com.example.TaskManager.config.UserSeeder;
import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TasksControllerTest {

  @Autowired
  TasksController tasksController;
  @Autowired
  TaskService taskService;

  private UserSeeder userSeeder;

  @BeforeEach
  void setup(){
    userSeeder.clearUserTable();
  }

//  @Test
//  void itShouldGetATaskById(){
//    User user = User.builder()
//        .userId(1L)
//        .email("")
//        .password("")
//        .firstname("")
//        .lastname("")
//        .role(User.Role.EMPLOYEE)
//        .build();
//
//    Task task = Task.builder()
//        .taskId(1L)
//        .title("test task")
//        .description("this is a test task")
//        .user(user)
//        .build();
//
//    taskService.saveTask(task);
//
//    Task task1 = tasksController.getTask(1L).getBody();
//
//    assertThat(task1.getTaskId()).isEqualTo(1L);
//    //assertThat(task1.getUser()).isEqualTo(user);
//  }
//
//  @Test
//  void itShouldSaveATaskById(){
//    User user = User.builder()
//        .userId(1L)
//        .email("")
//        .password("")
//        .firstname("")
//        .lastname("")
//        .role(User.Role.EMPLOYEE)
//        .build();
//
//    Task task = Task.builder()
//        .taskId(1L)
//        .title("test task")
//        .description("this is a test task")
//        .user(user)
//        .build();
//
//    tasksController.saveTask(task);
//
//    Task task1 = taskService.getTask(1L);
//
//    assertThat(task1.getTaskId()).isEqualTo(1L);
//    //assertThat(task1.getUser()).isEqualTo(user);
//  }
//
//  @Test
//  void itCanDeleteATask(){
//    User user = User.builder()
//        .userId(1L)
//        .email("")
//        .password("")
//        .firstname("")
//        .lastname("")
//        .role(User.Role.EMPLOYEE)
//        .build();
//
//    Task task = Task.builder()
//        .taskId(1L)
//        .title("test task")
//        .description("this is a test task")
//        .user(user)
//        .build();
//
//    taskService.saveTask(task);
//
//    Task task1 = tasksController.deleteTask(1L).getBody();
//
//    assertThat(task1.getTaskId()).isEqualTo(1L);
//    //assertThat(task1.getUser()).isEqualTo(user);
//  }
//
//  @Test
//  void itCanUpdateATask(){
//    User user = User.builder()
//        .userId(1L)
//        .email("")
//        .password("")
//        .firstname("")
//        .lastname("")
//        .role(User.Role.EMPLOYEE)
//        .build();
//
//    Task task = Task.builder()
//        .taskId(1L)
//        .title("test task")
//        .description("this is a test task")
//        .user(user)
//        .build();
//
//    taskService.saveTask(task);
//
//    Task task1 = tasksController.updateTask(1L, null, "updatedTitle", null, null).getBody();
//
//    assertThat(task1.getTaskId()).isEqualTo(1L);
//    assertThat(task1.getTitle()).isEqualTo("updatedTitle");
//  }
}
