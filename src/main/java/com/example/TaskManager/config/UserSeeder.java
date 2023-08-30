package com.example.TaskManager.config;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Configuration
@RequiredArgsConstructor
public class UserSeeder {


//  @Autowired
//  JdbcTemplate jdbcTemplate;
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  private List<User> usersList;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedUsersTable();
  }

  public void seedUsersTable() {

    clearUserTable();

//    for (int i = 1; i <= 10; i++) {
//      User user = new User();
//      user.setUserId(Long.valueOf(i));
//      user.setEmail("user" + i + "@example.com");
//      user.setPassword(passwordEncoder.encode("password" + i));
//      user.setFirstname("FirstName" + i);
//      user.setLastname("LastName" + i);
//      user.setRole(User.Role.EMPLOYEE);
//      user.setTasks(Set.of());// Adjust role as needed
//
//      userRepository.save(user);
//    }

//    String sql = "SELECT username, email FROM users U WHERE U.username = \"admin\" OR U.email = \"test@test.com\" LIMIT 1";
//    List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
//    if(u == null || u.size() <= 0) {
//      User user = new User();
//      user.setFirstname("Spring Blog");
//      user.setLastname("admin");
//      user.setEmail("test@test.com");
//      user.setPassword(new BCryptPasswordEncoder().encode("test123"));
//      //user.setRole(Roles.SUPER_ADMIN.toString());
//      userRepository.save(user);
//      System.out.println("Users Seeded");
//    } else {
//      System.out.println("Users Seeding Not Required");
//    }

  }
  public void clearUserTable(){
    userRepository.deleteAll();
  }
}
