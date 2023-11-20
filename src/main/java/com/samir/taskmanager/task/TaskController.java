package com.samir.taskmanager.task;

import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.dto.TaskDTO;
import com.samir.taskmanager.task.dto.TasksListDTO;
import com.samir.taskmanager.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/tasks")
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final DtoMapper dtoMapper;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> tasks = taskService.getTasksByUsername(getAuthenticatedUsername());
        TasksListDTO tasksListDTO = dtoMapper.mapTasksToTasksListDTO(tasks);
        return new ResponseEntity<>(tasksListDTO.getTasks(), HttpStatus.OK);
    }
    // get one task
    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewTask(@RequestBody Task task) {
        System.out.println(task);
        taskService.addNewTask(task, getAuthenticatedUsername());
        return ResponseEntity.ok("Task added successfully");
    }

    //update a task
    @PutMapping("{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable("taskId") Long id,@RequestBody Task newTask){
        taskService.updateTask(id, newTask);
        return ResponseEntity.ok("Task was updated successfully");
    }
    @PutMapping("complete/{taskId}")
    public ResponseEntity<String> completeTask(@PathVariable("taskId") Long id){
        taskService.completeTask(id);
        return ResponseEntity.ok("Task was completed successfully");
    }

    //delete a task
    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task was deleted successfully");
    }
    public String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
