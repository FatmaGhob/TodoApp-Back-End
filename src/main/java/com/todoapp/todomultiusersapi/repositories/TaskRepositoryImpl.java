package com.todoapp.todomultiusersapi.repositories;

import com.todoapp.todomultiusersapi.domain.Task;
import com.todoapp.todomultiusersapi.domain.User;
import com.todoapp.todomultiusersapi.exceptions.TaAuthException;
import com.todoapp.todomultiusersapi.exceptions.TaBadRequestException;
import com.todoapp.todomultiusersapi.exceptions.TaResourceNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private static final String SQL_CREATE = "INSERT INTO TA_TASKS(USER_ID, TASK_ID, TASK_CONTENT, COMPLETED) VALUES(?, NEXTVAL('TA_TASKS_SEQ'), ?, ?)";
    private static final  String SQL_FIND_BY_ID = "SELECT * FROM TA_TASKS WHERE USER_ID = ? AND TASK_ID = ? ";
    private static final String SQL_FIND_ALL = "SELECT * FROM TA_TASKS WHERE USER_ID = ?";
    private static final String SQL_UPDATE = "UPDATE TA_TASKS SET TASK_CONTENT = ? AND COMPLETED = ?" +
            " WHERE  TASK_ID = ? AND USER_ID = ? ";
    private static final String SQL_DELETE = " DELETE FROM TA_TASKS WHERE USER_ID = ? AND TASK_ID = ?";
    private static final String SQL_CLEAR = "DELETE FROM TA_TASKS WHERE USER_ID = ? ";






    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll(Integer userId) throws TaResourceNotFoundException {
        try{
            return jdbcTemplate.query(SQL_FIND_ALL, taskRowMapper , new Object[]{userId});



        }catch (EmptyResultDataAccessException e){
            throw  new TaResourceNotFoundException("TASK NOT FOUND");
        }
    }

    @Override
    public Task findById(Integer userId, Integer taskId) throws TaResourceNotFoundException {
        try{
            System.out.println(taskId);
            Task task = jdbcTemplate.queryForObject(SQL_FIND_BY_ID ,taskRowMapper, new Object[]{userId,taskId});

           return task;

        }catch (EmptyResultDataAccessException e){
            throw  new TaResourceNotFoundException("TASK NOT FOUND");
        }
    }

    @Override
    public Integer create(Integer userId, String content, Boolean completed) throws TaBadRequestException {
        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,userId);
                ps.setString(2,content);
                ps.setBoolean(3,completed);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("TASK_ID");

        }catch (Exception e){
            throw  new TaBadRequestException("Invalid details !. Failed to create task");
        }
    }

///////////////////////////// NOT WORKING /////////////////////////////////////////////////////////
    @Override
    public void update( Integer taskId, Integer userId, Task task) throws TaBadRequestException {
        try {

            jdbcTemplate.update(SQL_UPDATE, new Object[]{task.getContent(), task.getCompleted(), taskId , userId});

        }catch (Exception e){
            throw  new TaBadRequestException("Invalid request");
        }
    }


    @Override
    public void removeById(Integer userId, Integer taskId) throws TaResourceNotFoundException {
       // jdbcTemplate.(SQL_DELETE,Integer.class);
          int count = jdbcTemplate.update(SQL_DELETE , new Object[]{userId, taskId});
          if (count == 0) throw  new TaResourceNotFoundException("NOT FOUND");
    }

    @Override
    public void clear(Integer userId) throws TaResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_CLEAR , new Object[]{userId});
        if (count == 0) throw  new TaResourceNotFoundException("NOT FOUND");
    }

    private RowMapper<Task> taskRowMapper = ((rs, rowNum) -> {
        return new Task(
                rs.getInt("TASK_ID"),
                rs.getInt("USER_ID"),
                rs.getString("TASK_CONTENT"),
                rs.getBoolean("COMPLETED"));
    });
}
