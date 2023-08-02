package com.example.TaskManager.repository;

import com.example.TaskManager.models.User;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
//  @Query("SELECT * FROM users u WHERE u.email = :userName")
//  public Collection<User> findByUsername(@Param("userName") String userName);
}
