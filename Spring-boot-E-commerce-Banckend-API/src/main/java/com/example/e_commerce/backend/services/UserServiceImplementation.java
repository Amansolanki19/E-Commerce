package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User findUserById(Long id) throws UserException {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("Not user Found with id: "+id));
    }

    @Override
    public Optional<User> findByEmail(String email) throws UserException {
        return Optional.ofNullable(userRepo.findByEmail(email).orElseThrow(() -> new UserException("No user found with the email: " + email)));
    }
}
