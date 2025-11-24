package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
}
