package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.Ratings;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.repositories.RatingRepo;
import com.example.e_commerce.backend.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private ProductService productService;

    @Override
    public Ratings createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Ratings rating=new Ratings();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepo.save(rating);
    }

    @Override
    public List<Ratings> findRating(Long productId) {

        return ratingRepo.getAllRating(productId);
    }
}
