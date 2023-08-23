package com.example.TaskManager.service;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
