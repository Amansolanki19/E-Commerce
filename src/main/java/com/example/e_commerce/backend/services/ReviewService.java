package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Review;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest request, User user) throws ProductException;
    List<Review> getAll(Long productId);
}
