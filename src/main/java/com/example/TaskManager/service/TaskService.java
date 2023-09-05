package com.example.TaskManager.service;

import com.example.TaskManager.controller.AuthenticationRequest;
import com.example.TaskManager.controller.TaskRequest;
import com.example.TaskManager.controller.TaskResponse;
import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.repository.UserRepository;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class TaskService {

  @Autowired
  private final TaskRepository taskRepository;

  @Autowired
  private final UserRepository userRepository;

  public Task getTask(Long taskId){ return taskRepository.getReferenceById(taskId); }
  public TaskResponse saveTask(TaskRequest request){
    if( userRepository.findByEmail(request.getUserEmail()).isPresent() ) {
      User user = userRepository.findByEmail(request.getUserEmail()).get();

      Task task = Task.builder()
          .title(request.getTitle())
          .description(request.getDescription())
          .dueDate(request.getDueDate())
          .createdOn(Instant.now())
          .user(user)
          .build();

      taskRepository.save(task);
      return TaskResponse.builder().message("Task saved succesfully").build();
    }

    return TaskResponse.builder().message("User associated with task does not exist").build();
  }
  public TaskResponse updateTask(Long taskId, String userEmail, String title, String description, Timestamp dueDate, AuthenticationRequest request){
    if (validateRole(request)) {
      Task existingTask;
      try {
        existingTask = taskRepository.getReferenceById(taskId);
      } catch (Exception e) {
        throw new TaskNotFoundException("Task dosen't exist", e);
      }
      try {
        if (!StringUtils.isEmpty(userEmail)) {
          if (userRepository.findByEmail(userEmail).isPresent()) {
            existingTask.setUser(userRepository.findByEmail(userEmail).get());
          }
        }
      } catch (Exception e) {
        throw new UserNotFoundException("User with given email does not exist", e);
      }

      if (!StringUtils.isEmpty(title)) {
        existingTask.setTitle(title);
      }

      if (!StringUtils.isEmpty(description)) {
        existingTask.setDescription(description);
      }

      if (dueDate != null) {
        existingTask.setDueDate(dueDate);
      }
      Task updatedTask = taskRepository.save(existingTask);
      return TaskResponse.builder().message("Task updated successfully").build();
    }
    return TaskResponse.builder().message("You do not have permission to do this").build();
  }

  public TaskResponse deleteTask(Long taskId, AuthenticationRequest request){
    if (validateRole(request)) {
      try {
        Task task = taskRepository.getReferenceById(taskId);
        taskRepository.delete(task);
        return TaskResponse.builder().message("Task deleted successfully").build();
      } catch (Exception e) {
        throw new TaskNotFoundException("Task dosen't exist", e);
      }
    }
    return TaskResponse.builder().message("You do not have permission to do this").build();
  }

  private boolean validateRole(AuthenticationRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      if (userRepository.findByEmail(request.getPassword()).get().getRole().equals(String.valueOf(User.Role.ADMIN))) {
        return true;
      }
    }
    return false;
  }
}
