package com.samir.taskmanager.task.repository;

import com.samir.taskmanager.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
