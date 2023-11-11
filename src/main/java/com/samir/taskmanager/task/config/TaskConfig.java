package com.samir.taskmanager.task.config;


import com.samir.taskmanager.task.model.Priority;
import com.samir.taskmanager.task.model.Status;
import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository){
        return args -> {
            Task task1 = new Task(
                    "task 1",
                    "task 1.5 description",
                    LocalDate.of(2024, Month.JANUARY, 1),
                    Priority.High,
                    Status.IN_PROGRESS
            );
            Task task2 = new Task(
                    "task 2",
                    "task 2 description",
                    LocalDate.of(2024, Month.JANUARY, 2),
                    Priority.Low,
                    Status.IN_PROGRESS
            );
            repository.saveAll(List.of(task1, task2));
        };
    }
}

