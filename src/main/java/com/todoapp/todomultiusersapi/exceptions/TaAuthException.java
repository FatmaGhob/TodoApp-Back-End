package com.todoapp.todomultiusersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TaAuthException extends RuntimeException {

    public  TaAuthException(String message){
        super(message);
    }
}
