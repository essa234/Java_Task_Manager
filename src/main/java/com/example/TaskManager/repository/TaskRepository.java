package com.example.TaskManager.repository;

import com.example.TaskManager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
