package com.example.TaskManager.repository;

import com.example.TaskManager.models.Task;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TaskRepository extends JpaRepository<Task, Long> {

  //@Query("select * from tasks t where t.taskId = :id")
  //Optional<List<Task>> findTasksbyId(@Param("id") Long id);

  @Query("select * from tasks t where t.title = :title")
  Collection<Task> findTasksbyTitle(@Param("title") String title);
}
