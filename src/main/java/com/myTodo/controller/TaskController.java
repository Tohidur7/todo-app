package com.myTodo.controller;

import com.myTodo.payload.TaskDto;
import com.myTodo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TaskController {

    private TaskService taskService ;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(value = "id") long id) {

        taskService.deleteTask(id);
        return new ResponseEntity<>("entity deleted successfully", HttpStatus.OK);
    }

    @PutMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable(value = "id") long id, @RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.updateTask(id, taskDto), HttpStatus.OK);
    }

    @PutMapping(path = "/tasks/{id}/completed/{status}")
    public ResponseEntity<TaskDto> markAsComplete(@PathVariable(value = "id") long id, @PathVariable(value = "status") boolean status) {

        return new ResponseEntity<>(taskService.updateStatus(id,status), HttpStatus.OK);
    }


    @GetMapping(path = "/tasks")
    public ResponseEntity<List<TaskDto>> getAllTask() {

        return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK) ;
    }


}
