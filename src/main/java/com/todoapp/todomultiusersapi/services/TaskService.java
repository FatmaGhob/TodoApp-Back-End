package com.todoapp.todomultiusersapi.services;

import com.todoapp.todomultiusersapi.domain.Task;
import com.todoapp.todomultiusersapi.exceptions.TaBadRequestException;
import com.todoapp.todomultiusersapi.exceptions.TaResourceNotFoundException;

import java.util.List;

public interface TaskService {

        List<Task> getAllTasks(Integer userId);
        Task getTaskById(Integer userId, Integer taskId) throws TaResourceNotFoundException;
        Task addTask(Integer userId, String content, boolean completed) throws TaBadRequestException;
        void updateTask(Integer taskId, Integer userId, Task task) throws TaBadRequestException;
        void deleteTask(Integer taskId, Integer userId) throws TaResourceNotFoundException;
        void clearTasks(Integer userId) throws TaResourceNotFoundException;

}
