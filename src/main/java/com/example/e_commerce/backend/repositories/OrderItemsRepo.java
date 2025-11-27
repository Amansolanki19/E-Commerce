package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItems, Long> {
}
