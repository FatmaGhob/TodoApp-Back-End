package com.todoapp.todomultiusersapi.services;

import com.todoapp.todomultiusersapi.domain.Task;
import com.todoapp.todomultiusersapi.exceptions.TaBadRequestException;
import com.todoapp.todomultiusersapi.exceptions.TaResourceNotFoundException;
import com.todoapp.todomultiusersapi.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(Integer userId) {
        return taskRepository.findAll(userId);
    }

    @Override
    public Task getTaskById(Integer userId, Integer taskId) throws TaResourceNotFoundException {
        return taskRepository.findById(userId, taskId);
    }

    @Override
    public Task addTask( Integer userId, String content, boolean completed) throws TaBadRequestException {
        int taskId  = taskRepository.create(userId,content, completed);
        return taskRepository.findById(userId,taskId);

    }




    @Override
    public void updateTask(Integer taskId, Integer userId, Task task) throws TaBadRequestException {
        taskRepository.update(taskId,userId,task);
    }

    @Override
    public void deleteTask(Integer taskId, Integer userId) throws TaResourceNotFoundException {
        taskRepository.removeById(taskId,userId);
    }

    @Override
    public void clearTasks(Integer userId) throws TaResourceNotFoundException {
        taskRepository.clear(userId);
    }
}
