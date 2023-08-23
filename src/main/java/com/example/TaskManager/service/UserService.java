package com.example.TaskManager.service;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

//  public User loadByUsername(String username){
//    return userRepository.findByUsername(username).iterator().next();
//  }

  public User save(User user) { return userRepository.save(user); }
  public User getUser(Long userId){
    return userRepository.getReferenceById(userId);
  }

  public User deleteUser(Long userId){
    User existingUser;
    try {
      existingUser = userRepository.findById(userId).get();
    } catch (Exception e){
      throw new UserNotFoundException("User not found", e);
    }
    userRepository.delete(existingUser);
    return existingUser;
  }

  public User updateUser(Long userId, String password, String email, String firstname, String lastname, String role) {
    User existingUser;
    try {
      existingUser = userRepository.findById(userId).get();
    } catch (Exception e){
      throw new UserNotFoundException("User not found", e);
    }

    if(!StringUtils.isEmpty(password)){
      String newPassword = passwordEncoder.encode(password);
      existingUser.setPassword(newPassword);
    }

    if(!StringUtils.isEmpty(email)) {
      existingUser.setEmail(email);
    }

    if(!StringUtils.isEmpty(firstname)) {
      existingUser.setFirstname(firstname);
    }

    if(!StringUtils.isEmpty(lastname)) {
      existingUser.setLastname(lastname);
    }

    if(!StringUtils.isEmpty(role)) {

      if(role.equals("EMPLOYEE")){
        existingUser.setRole(User.Role.EMPLOYEE);
      }

      if(role.equals("MANAGER")){
        existingUser.setRole(User.Role.MANAGER);
      } else {
        //throw new InvalidAssignmentException("This role does not exist please try again");
      }

    }
    User updatedUser = userRepository.save(existingUser);
    return updatedUser;
  }
}
