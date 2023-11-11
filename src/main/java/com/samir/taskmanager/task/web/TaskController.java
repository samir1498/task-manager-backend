package com.samir.taskmanager.task.web;

import com.samir.taskmanager.task.model.Task;
import com.samir.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    // get tasks
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.OK);
    }
    // get one task
    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewTask(@RequestBody Task task) {
        taskService.addNewTask(task);
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
}
