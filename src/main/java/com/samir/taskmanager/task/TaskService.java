package com.samir.taskmanager.task;

import com.samir.taskmanager.task.model.Priority;
import com.samir.taskmanager.task.model.Status;
import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.TaskRepository;
import com.samir.taskmanager.user.UserRepository;
import com.samir.taskmanager.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> getTasksByUsername(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task getTaskById(Long id){
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id "
                + id + " does not exists"));
    }

    public void addNewTask(Task task, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            task.setUser(user);
            taskRepository.save(task);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void updateTask(Long id, Task newTask) {
        taskRepository.findById(id).ifPresent(task -> {
            if (newTask.getTitle() != null) {
                task.setTitle(newTask.getTitle());
            }
            if (newTask.getDescription() != null) {
                task.setDescription(newTask.getDescription());
            }
            if (newTask.getDueDate() != null) {
                task.setDueDate(newTask.getDueDate());
            }
            if (newTask.getPriority() != null) {
                // Check if the provided priority value is valid
                if (EnumSet.allOf(Priority.class).contains(newTask.getPriority())) {
                    task.setPriority(newTask.getPriority());
                } else {
                    // Handle the case of an invalid priority value
                    throw new IllegalArgumentException("Invalid priority value provided.");
                }
            }
            if (newTask.getStatus() != null) {
                // Check if the provided status value is valid
                if (EnumSet.allOf(Status.class).contains(newTask.getStatus())) {
                    task.setStatus(newTask.getStatus());
                } else {
                    // Handle the case of an invalid status value
                    throw new IllegalArgumentException("Invalid status value provided.");
                }
            }
            taskRepository.save(task);
        });
    }
@Transactional
    public void completeTask(Long id) {
        taskRepository.findById(id).ifPresent(task -> {
                    task.setStatus(Status.COMPLETED);
            taskRepository.save(task);
        });
    }

}
