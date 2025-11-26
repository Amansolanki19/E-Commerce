package com.example.e_commerce.backend.services;


import com.example.e_commerce.backend.entity.Ratings;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Ratings createRating(RatingRequest request, User user)throws ProductException;
    List<Ratings> findRating(Long productId);
}
