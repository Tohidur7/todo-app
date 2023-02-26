package com.myTodo.service.impl;


import com.myTodo.payload.TaskDto;
import com.myTodo.entity.Task;
import com.myTodo.exception.ResourceNotFoundException;
import com.myTodo.repository.TaskRepository;
import com.myTodo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository ;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }



    @Override
    public TaskDto createTask(TaskDto taskDto) {

        //convert dto into entity
        Task task = mapToEntity(taskDto);

        Task taskResponse = taskRepository.save(task);

        // convert entity into dto
        return mapToDto(taskResponse);
    }

    @Override
    public void deleteTask(long id) {

        //get task by id from the database
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", id));

        taskRepository.delete(task);
    }

    @Override
    public TaskDto updateTask(long id, TaskDto taskDto) {

        //get task by id from the database
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", id));

        //update the data
        task.setContent(taskDto.getContent());

        //save data to database
        Task taskResponse = taskRepository.save(task);

        //convert to dto
        return mapToDto(taskResponse);
    }

    @Override
    public TaskDto updateStatus(long id, boolean status) {

        //get task by id from the database
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", id));

        //marking the status as completed
        task.setCompleted(status);

        //save entity to database
        Task taskResponse = taskRepository.save(task);

        //convert to dto
        return mapToDto(taskResponse);
    }


    @Override
    public List<TaskDto> getAllTask() {

        //get Task from the database
        List<Task> taskList = taskRepository.findAll();

        //converting list of task into list of taskDto
        return taskList.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
    }


    // CONVERTS DTO INTO ENTITY
    private Task mapToEntity(TaskDto taskDto) {

        Task task = new Task();

        task.setContent(taskDto.getContent());
        task.setCompleted(taskDto.getCompleted());

        return task ;
    }


    // CONVERTS ENTITY INTO DTO
    private TaskDto mapToDto(Task task) {

        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setContent(task.getContent());
        taskDto.setCompleted(task.getCompleted());

        return taskDto ;
    }
}
