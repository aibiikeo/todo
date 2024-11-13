package com.example.todo.dto;

import com.example.todo.model.Priority;
import com.example.todo.model.Status;
import lombok.*;

@Data
@Builder
public class TaskUpdateDto {
    private String title;
    private String description;
    private Priority priority;
    private Status status;
}
