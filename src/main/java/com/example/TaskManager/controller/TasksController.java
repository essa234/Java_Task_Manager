package com.example.TaskManager.controller;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.service.TaskService;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

  @Autowired
  TaskService taskService;

  @PostMapping("/save")
  public ResponseEntity<Task> saveTask(@RequestBody Task task) {
    return ResponseEntity.ok(taskService.saveTask(task));
  }

  @GetMapping()
  public ResponseEntity<Task> getTask(@RequestBody Long taskId) {
    return ResponseEntity.ok(taskService.getTask(taskId));
  }

  @PatchMapping("/user")
  public ResponseEntity<Task> updateTask(@RequestParam Long taskId,
                                         @RequestParam(required = false) User user,
                                         @RequestParam(required = false) String title,
                                         @RequestParam(required = false) String description,
                                         @RequestParam(required = false) Timestamp dueDate){
    return ResponseEntity.ok(taskService.updateTask(taskId,user,title,description,dueDate));
  }
  @DeleteMapping()
  public ResponseEntity<Task> deleteTask(@RequestBody Long taskId){
    return ResponseEntity.ok(taskService.deleteTask(taskId));
  }

}
