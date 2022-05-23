package com.todoapp.todomultiusersapi.resources;

import com.todoapp.todomultiusersapi.domain.Task;
import com.todoapp.todomultiusersapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {

    @Autowired
    TaskService taskService;


    @GetMapping("")
    public String getTasks(HttpServletRequest request){
        int userId= (Integer) request.getAttribute("userId");

        return "Authenticated! UserId: " + userId;
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(HttpServletRequest request ,
                                        @PathVariable ("taskId") Integer taskId)
    {
        int userId= (Integer) request.getAttribute("userId");
        Task task = taskService.getTaskById(userId,taskId);
        System.out.println(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }




    @GetMapping("/all")
    public  ResponseEntity<List<Task>> getAllTasks(HttpServletRequest request){
        int userId= (Integer) request.getAttribute("userId");
        List<Task> task = taskService.getAllTasks(userId);
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }




    @PostMapping("")
    public ResponseEntity<Task> addTask(HttpServletRequest request ,
                                        @RequestBody Map<String, Object> taskMap)
    {

        int userId= (Integer) request.getAttribute("userId");
        String content = (String) taskMap.get("content");
        Boolean completed = (Boolean) taskMap.get("completed");

        Task task = taskService.addTask(userId,content,completed);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Map<String,Boolean>> updateTask(HttpServletRequest request ,
                                        @PathVariable("taskId") Integer taskId,
                                           @RequestBody Task task)
    {
        int userId= (Integer) request.getAttribute("userId");
        taskService.updateTask(taskId,userId,task);
        Map<String,Boolean> map = new HashMap<>();
        map.put("Success" , true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Map<String,Boolean>> deleteTask(HttpServletRequest request ,
                                                          @PathVariable("taskId") Integer taskId)
    {
        int userId= (Integer) request.getAttribute("userId");
        taskService.deleteTask(userId,taskId);
        Map<String,Boolean> map = new HashMap<>();
        map.put("Success" , true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Map<String,Boolean>> clearTasks(HttpServletRequest request)
    {
        int userId= (Integer) request.getAttribute("userId");
        taskService.clearTasks(userId);
        Map<String,Boolean> map = new HashMap<>();
        map.put("Success" , true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
