package com.example.TaskManager.service;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

//  public User loadByUsername(String username){
//    return userRepository.findByUsername(username).iterator().next();
//  }

  public User updateEmail(Long userId, String newEmail) {
    User existingUser;
    try {
      existingUser = userRepository.findById(userId).get();
    } catch (Exception e){
      throw new UsernameNotFoundException("User not found");
    }
    existingUser.setEmail(newEmail);
    User updatedUser = userRepository.save(existingUser);
    return updatedUser;
  }

  public User updatePassword(Long userId, String newPassword) {
    User existingUser;
    try {
      existingUser = userRepository.findById(userId).get();
    } catch (Exception e){
      throw new UsernameNotFoundException("User not found");
    }
    existingUser.setPassword(passwordEncoder.encode(newPassword));
    User updatedUser = userRepository.save(existingUser);
    return updatedUser;
  }

  public User updateRole(Long userId, String role){
    User existingUser;
    try {
      existingUser = userRepository.findById(userId).get();
    } catch (Exception e){
      throw new UsernameNotFoundException("User not found");
    }
    if(role == User.Role.EMPLOYEE.toString()  || role == User.Role.MANAGER.toString()){
      existingUser.setRole(User.Role.valueOf(role));
    }
    User updatedUser = userRepository.save(existingUser);
    return updatedUser;
  }
}
