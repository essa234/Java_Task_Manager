package com.example.TaskManager.service;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

//  public User loadByUsername(String username){
//    return userRepository.findByUsername(username).iterator().next();
//  }
}
