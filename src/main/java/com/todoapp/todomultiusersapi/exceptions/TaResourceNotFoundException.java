package com.todoapp.todomultiusersapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaResourceNotFoundException extends RuntimeException {
    public  TaResourceNotFoundException(String message){ super(message);}
}
