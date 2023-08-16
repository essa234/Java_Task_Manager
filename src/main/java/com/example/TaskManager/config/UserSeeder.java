package com.example.TaskManager.config;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserSeeder {


  @Autowired
  JdbcTemplate jdbcTemplate;
  UserRepository userRepository;

  private List<User> usersList;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedUsersTable();
  }

  private void seedUsersTable() {
    clearUserTable();
    String sql = "SELECT username, email FROM users U WHERE U.username = \"admin\" OR U.email = \"test@test.com\" LIMIT 1";
    List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
    if(u == null || u.size() <= 0) {
      User user = new User();
      user.setFirstname("Spring Blog");
      user.setLastname("admin");
      user.setEmail("test@test.com");
      user.setPassword(new BCryptPasswordEncoder().encode("test123"));
      //user.setRole(Roles.SUPER_ADMIN.toString());
      userRepository.save(user);
      System.out.println("Users Seeded");
    } else {
      System.out.println("Users Seeding Not Required");
    }
  }

  private void createUsers(){}


  private void clearUserTable(){
    String sql = "DELETE FROM users";
    jdbcTemplate.execute(sql);
  }
}
