package com.example.TaskManager.service;

import com.example.TaskManager.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

  @Autowired
  private ManagerRepository managerRepository;
}
