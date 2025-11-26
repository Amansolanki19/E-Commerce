package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.Review;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.repositories.ProductRepo;
import com.example.e_commerce.backend.repositories.ReviewRepo;
import com.example.e_commerce.backend.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException{
        Product product = productService.findProductById(request.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());
        review.setReviewedAt(LocalDateTime.now());
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getAll(Long productId) {
        return reviewRepo.getAllReviews(productId);
    }
}
