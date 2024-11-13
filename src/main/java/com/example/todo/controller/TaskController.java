package com.example.todo.controller;

import com.example.todo.dto.SuccessDto;
import com.example.todo.dto.TaskUpdateDto;
import com.example.todo.entity.TaskEntity;
import com.example.todo.exception.ApiException;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/get-all")
    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public TaskEntity getById(@PathVariable("id") Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ApiException("Task with id " + id + " is not found", HttpStatusCode.valueOf(404)));
    }

    @PostMapping("/create")
    public TaskEntity create(@RequestBody TaskEntity newLandmark) {
        return taskRepository.save(newLandmark);
    }


    @PutMapping("update/{id}")
    public TaskEntity update(@RequestBody TaskUpdateDto task, @PathVariable("id") Long id) {
        TaskEntity toUpdate = taskRepository.findById(id).orElseThrow(() -> new ApiException("Task with id " + id + " is not found", HttpStatusCode.valueOf(404)));
        if (task.getTitle() != null) {
            toUpdate.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            toUpdate.setDescription(task.getDescription());
        }
        if (task.getPriority() != null) {
            toUpdate.setPriority(task.getPriority());
        }
        if (task.getStatus() != null) {
            toUpdate.setStatus(task.getStatus());
        }

        return taskRepository.save(toUpdate);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete (@PathVariable Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new ApiException("Task with id " + id + " is not found", HttpStatusCode.valueOf(404)));
        taskRepository.delete(task);
        return new SuccessDto(true);
    }
}
