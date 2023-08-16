package com.example.TaskManager;

import com.example.TaskManager.gui.TaskManagerGui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
//		System.setProperty("java.awt.headless", "false");
//		TaskManagerGui.main(null);
	}

}
