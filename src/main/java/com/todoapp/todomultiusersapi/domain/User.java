package com.todoapp.todomultiusersapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Data
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
