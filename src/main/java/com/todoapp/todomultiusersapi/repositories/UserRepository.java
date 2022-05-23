package com.todoapp.todomultiusersapi.repositories;

import com.todoapp.todomultiusersapi.domain.User;
import com.todoapp.todomultiusersapi.exceptions.TaAuthException;

public interface UserRepository {

     // take all user parameter and return user id
    Integer create(String firstName,String lastName,String email , String password) throws TaAuthException;

    User findByEmailAndPassword(String email , String password) throws TaAuthException;

    // to check email is used or not
    Integer getCountByEmail(String email);


    User findById(Integer userId);




}
