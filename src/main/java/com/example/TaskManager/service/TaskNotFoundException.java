package com.example.TaskManager.service;

public class TaskNotFoundException extends RuntimeException{
  public TaskNotFoundException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
