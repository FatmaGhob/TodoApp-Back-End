package com.todoapp.todomultiusersapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Setter
@Getter
public class Task {
    private Integer taskId;
    private Integer userId;
    private String content;
    private Boolean completed;

}
