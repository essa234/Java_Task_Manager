package com.example.TaskManager.controller;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/save")
  public ResponseEntity<Task> saveTask(@RequestBody Task task) {
    return ResponseEntity.ok(taskService.saveTask(task));
  }
  public ResponseEntity<Task> getTask(@RequestBody Long id) {
    return ResponseEntity.ok(taskService.getTask(id));
  }


}
