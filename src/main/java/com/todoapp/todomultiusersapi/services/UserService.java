package com.todoapp.todomultiusersapi.services;

import com.todoapp.todomultiusersapi.domain.User;
import com.todoapp.todomultiusersapi.exceptions.TaAuthException;

public interface UserService {

    User validateUser(String email , String password) throws TaAuthException;

    User registerUser(String firstName, String lastName, String email , String password) throws TaAuthException;

}
