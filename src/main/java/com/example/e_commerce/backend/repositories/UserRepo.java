package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
     User findByEmail(String email);
}
