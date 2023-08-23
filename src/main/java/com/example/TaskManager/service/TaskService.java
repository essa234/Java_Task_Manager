package com.example.TaskManager.service;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.TaskRepository;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public Task getTask(Long taskId){ return taskRepository.getReferenceById(taskId); }
  public Task saveTask(Task task){ return taskRepository.save(task); }
  public List<Task> getAllTasks(){ return taskRepository.findAll(); }
  public List<Task> getAllTasksForId(Long id){
    //Optional<List<Task>> tasks = taskRepository.findTasksbyId(id);
    //return tasks.isPresent() ? tasks.get() : List.of(); }
    return null; }

  public Collection<Task> getTasksByTitle(String title){
    return taskRepository.findTasksbyTitle(title);
  }

  public Task updateTask(Long taskId, User user, String title, String description, Timestamp dueDate){
    Task existingTask;
    try {
      existingTask = taskRepository.getReferenceById(taskId);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
    if (user != null) {
      existingTask.setUser(user);
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
    return updatedTask;
  }
  public Task updateUserForTask(Long taskId, User user){
    Task existingTask;
    try {
      existingTask = taskRepository.getReferenceById(taskId);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
    existingTask.setUser(user);
    Task updatedTask = taskRepository.save(existingTask);
    return updatedTask;
  }

  public Task updateTitleForTask(Long taskId, String title){
    Task existingTask;
    try {
      existingTask = taskRepository.getReferenceById(taskId);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
    existingTask.setTitle(title);
    Task updatedTask = taskRepository.save(existingTask);
    return updatedTask;
  }

  public Task updateDescriptionForTask(Long taskId, String description){
    Task existingTask;
    try {
      existingTask = taskRepository.getReferenceById(taskId);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
    existingTask.setDescription(description);
    Task updatedTask = taskRepository.save(existingTask);
    return updatedTask;
  }

  public Task updateDueDateForTask(Long taskId, Timestamp dueDate){
    Task existingTask;
    try {
      existingTask = taskRepository.getReferenceById(taskId);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
    existingTask.setDueDate(dueDate);
    Task updatedTask = taskRepository.save(existingTask);
    return updatedTask;
  }

  public Task deleteTask(Long taskId){
    try {
      Task task = taskRepository.getReferenceById(taskId);
      taskRepository.delete(task);
      return task;
    } catch (Exception e){
      throw new TaskNotFoundException("Task dosen't exist", e);
    }
  }

}
