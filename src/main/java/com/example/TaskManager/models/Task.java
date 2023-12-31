package com.example.TaskManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue
  private Long taskId;
  private String title;
  private String description;
  private Timestamp dueDate;
  private Instant createdOn;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @PrePersist
  public void prePersist() {
    this.createdOn = Instant.now(); // Default value before entity is persisted
  }

}
