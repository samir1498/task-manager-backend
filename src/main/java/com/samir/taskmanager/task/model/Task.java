package com.samir.taskmanager.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samir.taskmanager.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;
    private String title;
    private String description;

    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public Task(String title, String description, LocalDate dueDate, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
}
