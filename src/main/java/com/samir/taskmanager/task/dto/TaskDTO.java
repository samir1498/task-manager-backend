package com.samir.taskmanager.task.dto;

import com.samir.taskmanager.task.model.Priority;
import com.samir.taskmanager.task.model.Status;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;

}
