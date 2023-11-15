package com.samir.taskmanager.task.dto;


import com.samir.taskmanager.task.dto.TaskDTO;
import lombok.Data;

import java.util.List;

@Data
public class TasksListDTO {

    private List<TaskDTO> tasks;

    public TasksListDTO(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

}

