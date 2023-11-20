package com.samir.taskmanager.config;

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


public class ApplicationConfig {

//    private final PasswordEncoder encoder;
//    @Bean
//    CommandLineRunner commandLineRunner(
//            TaskRepository taskRepository,
//            UserRepository userRepository
//    ){
//        return args -> {
//
//            Task task1 = new Task(
//                    "task 1",
//                    "task 1.5 description",
//                    LocalDate.of(2024, Month.JANUARY, 1),
//                    Priority.High,
//                    Status.IN_PROGRESS
//            );
//            Task task2 = new Task(
//                    "task 2",
//                    "task 2 description",
//                    LocalDate.of(2024, Month.JANUARY, 2),
//                    Priority.Low,
//                    Status.IN_PROGRESS
//            );
//            User samir = new User();
//            samir.setUsername("samir");
//            samir.setPassword(encoder.encode("samir"));
//            samir.setRole(Role.USER);
//
//            User mounir = new User();
//            mounir.setUsername("mounir");
//            mounir.setPassword(encoder.encode("mounir"));
//            mounir.setRole(Role.USER);
//            // Save users first
//            // Set tasks to users and users to tasks
//            task1.setUser(samir);
//            task2.setUser(mounir);
//            samir.setTaskList(List.of(task1));
//            mounir.setTaskList(List.of(task2));
//
//            // Save users and tasks
//            userRepository.saveAll(List.of(samir, mounir));
//            taskRepository.saveAll(List.of(task1, task2));
//
//        };
//    }
}
