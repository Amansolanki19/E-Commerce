package com.example.e_commerce.backend.services;


import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.UserException;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User findUserById(Long id)throws UserException;
    Optional<User> findByEmail(String email)throws UserException;
}
