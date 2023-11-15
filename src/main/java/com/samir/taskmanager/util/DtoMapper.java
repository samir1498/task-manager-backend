package com.samir.taskmanager.util;

import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.dto.TaskDTO;
import com.samir.taskmanager.task.dto.TasksListDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public TasksListDTO mapTasksToTasksListDTO(List<Task> tasks) {
        List<TaskDTO> taskDTOs = mapTasksToTaskDTOs(tasks);
        return new TasksListDTO(taskDTOs);
    }

    public List<TaskDTO> mapTasksToTaskDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO mapTaskToTaskDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .build();
    }
}
