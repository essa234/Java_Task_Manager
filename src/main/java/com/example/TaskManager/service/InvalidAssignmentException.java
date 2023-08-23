package com.example.TaskManager.service;

public class InvalidAssignmentException extends RuntimeException{
  public InvalidAssignmentException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
