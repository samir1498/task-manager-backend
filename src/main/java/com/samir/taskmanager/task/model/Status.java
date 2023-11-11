package com.samir.taskmanager.task.model;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Status {
    COMPLETED("Completed"),
    IN_PROGRESS("In Progress");  // Enum constant with the desired space

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

}


