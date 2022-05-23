package com.todoapp.todomultiusersapi.services;

import com.todoapp.todomultiusersapi.domain.User;
import com.todoapp.todomultiusersapi.exceptions.TaAuthException;
import com.todoapp.todomultiusersapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User validateUser(String email, String password) throws TaAuthException {
        if(email != null) email = email.toLowerCase(); // for case-insensitive
        return userRepository.findByEmailAndPassword(email, password);

    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws TaAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase(); // for case-insensitive
        if(!pattern.matcher(email).matches())
            throw new TaAuthException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0 ) throw new TaAuthException(" Email already in use ");

        Integer userId  = userRepository.create(firstName, lastName, email, password);

        return userRepository.findById(userId);


    }
}
