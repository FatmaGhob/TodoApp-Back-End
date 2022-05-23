package com.todoapp.todomultiusersapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaBadRequestException extends RuntimeException{

     public  TaBadRequestException(String message){ super(message);}
}

