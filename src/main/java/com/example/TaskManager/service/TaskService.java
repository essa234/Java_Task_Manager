package com.example.TaskManager.service;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
