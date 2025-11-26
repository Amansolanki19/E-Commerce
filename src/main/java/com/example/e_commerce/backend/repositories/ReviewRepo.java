package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepo extends JpaRepository<Review,Long> {

    @Query("SELECT p FROM Review p WHERE p.product.id=:productId")
    List<Review> getAllReviews(Long productId);
}
