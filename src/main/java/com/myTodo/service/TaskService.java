package com.myTodo.service;

import com.myTodo.payload.TaskDto;

import java.util.List;

public interface TaskService {


    TaskDto createTask(TaskDto taskDto);

    void deleteTask(long id);

    TaskDto updateTask(long id, TaskDto taskDto);

    TaskDto updateStatus(long id, boolean status);

    List<TaskDto> getAllTask();

}
