package com.samir.taskmanager.task.service;

import com.samir.taskmanager.task.model.Priority;
import com.samir.taskmanager.task.model.Status;
import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id "
                + id + " does not exists"));
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
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
