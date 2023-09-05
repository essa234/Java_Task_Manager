package com.example.TaskManager.service;

import com.example.TaskManager.controller.AuthenticationRequest;
import com.example.TaskManager.controller.UserResponse;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

//  public User loadByUsername(String username){
//    return userRepository.findByUsername(username).iterator().next();
//  }

  public User save(User user) { return userRepository.save(user); }
  public User getUser(String email, AuthenticationRequest request){
    if (validateRole(request) && userRepository.findByEmail(email).isPresent() ) {
      return userRepository.findByEmail(email).get();
    }
    return null;
  }

  public UserResponse deleteUser(Long userId, AuthenticationRequest request){
    if (validateRole(request)) {
      User existingUser;
      try {
        existingUser = userRepository.findById(userId).get();
      } catch (Exception e) {
        throw new UserNotFoundException("User not found", e);
      }
      userRepository.delete(existingUser);
      return UserResponse.builder().message("User deleted successfully").build();
    }
    return UserResponse.builder().message("You do not have permission to do this").build();
  }

  public UserResponse updateUser(Long userId, String password, String email, String firstname, String lastname, String role, AuthenticationRequest request) {
    if (validateRole(request)) {
      User existingUser;
      try {
        existingUser = userRepository.findById(userId).get();
      } catch (Exception e) {
        throw new UserNotFoundException("User not found", e);
      }

      if (!StringUtils.isEmpty(password)) {
        String newPassword = passwordEncoder.encode(password);
        existingUser.setPassword(newPassword);
      }

      if (!StringUtils.isEmpty(email)) {
        existingUser.setEmail(email);
      }

      if (!StringUtils.isEmpty(firstname)) {
        existingUser.setFirstname(firstname);
      }

      if (!StringUtils.isEmpty(lastname)) {
        existingUser.setLastname(lastname);
      }

      if (!StringUtils.isEmpty(role)) {

        if (role.equals("EMPLOYEE")) {
          existingUser.setRole(String.valueOf(User.Role.EMPLOYEE));
        }

        if (role.equals("MANAGER")) {
          existingUser.setRole(String.valueOf(User.Role.MANAGER));
        } else {
          //throw new InvalidAssignmentException("This role does not exist please try again");
        }

      }
      User updatedUser = userRepository.save(existingUser);
      return UserResponse.builder().message("User updated successfully").build();
    }
    return UserResponse.builder().message("You do not have permission to do this").build();
  }

  private boolean validateRole(AuthenticationRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      if (userRepository.findByEmail(request.getEmail()).get().getRole().equals(String.valueOf(User.Role.ADMIN))) {
        return true;
      }
    }
    return false;
  }
}
