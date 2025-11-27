package com.example.e_commerce.backend.controller;


import com.example.e_commerce.backend.entity.Ratings;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.request.RatingRequest;
import com.example.e_commerce.backend.services.RatingService;
import com.example.e_commerce.backend.services.UserService;
import com.example.e_commerce.backend.Configuration.JwtProvider;
import com.example.e_commerce.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<Ratings> createRating(@RequestHeader("Authorization") String jwt, @RequestBody RatingRequest request)
    throws UserException, ProductException {
        String email = jwtProvider.getEmail(jwt);
        User user = userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        Ratings ratings = ratingService.createRating(request, user);
        return ResponseEntity.ok(ratings);

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Ratings>> getRatings(@PathVariable Long productId, @RequestHeader("Authorization")
                                                    String jwt)throws UserException,ProductException{
        String email = jwtProvider.getEmail(jwt);
        userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        List<Ratings> ratings = ratingService.findRating(productId);
        return ResponseEntity.ok(ratings);
    }


}
