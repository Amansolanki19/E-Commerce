package com.example.e_commerce.backend.repositories;


import com.example.e_commerce.backend.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Ratings,Long> {
    @Query("SELECT r FROM Ratings r WHERE r.product.id=:productId")
    List<Ratings> getAllRating(@Param("productId") Long productId);
}
