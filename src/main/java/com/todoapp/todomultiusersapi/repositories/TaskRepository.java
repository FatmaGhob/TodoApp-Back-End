package com.todoapp.todomultiusersapi.repositories;

import com.todoapp.todomultiusersapi.domain.Task;
import com.todoapp.todomultiusersapi.exceptions.TaAuthException;
import com.todoapp.todomultiusersapi.exceptions.TaBadRequestException;
import com.todoapp.todomultiusersapi.exceptions.TaResourceNotFoundException;

import java.util.List;

public interface TaskRepository {


    List<Task> findAll(Integer userId) throws TaResourceNotFoundException;
    Task  findById(Integer userId , Integer taskId) throws TaResourceNotFoundException;
    Integer  create(Integer userId, String content, Boolean completed) throws TaBadRequestException;
    void update(Integer userId, Integer taskId,  Task task) throws TaBadRequestException;
    void removeById(Integer userId, Integer taskId) throws  TaResourceNotFoundException;
    void clear(Integer userId) throws TaResourceNotFoundException;

}
