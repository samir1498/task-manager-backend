package com.samir.taskmanager.Config;

import com.samir.taskmanager.task.model.Priority;
import com.samir.taskmanager.task.model.Status;
import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.TaskRepository;
import com.samir.taskmanager.user.model.Role;
import com.samir.taskmanager.user.model.User;
import com.samir.taskmanager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final PasswordEncoder encoder;
    @Bean
    CommandLineRunner commandLineRunner(
            TaskRepository taskRepository,
            UserRepository userRepository
    ){
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
            User samir = new User(
                "samir",
                "samir@gmail.com",
                encoder.encode("samir"),
                    Role.USER

        );
            User mounir = new User(
                    "mounir",
                    "mounir@gmail.com",
                    encoder.encode("mounir"),
                    Role.USER
            );
            // Save users first
            // Set tasks to users and users to tasks
            task1.setUser(samir);
            task2.setUser(mounir);
            samir.setTaskList(List.of(task1));
            mounir.setTaskList(List.of(task2));

            // Save users and tasks
            userRepository.saveAll(List.of(samir, mounir));
            taskRepository.saveAll(List.of(task1, task2));

        };
    }
}
