package com.example.e_commerce.backend.controller;


import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.request.AddItems;
import com.example.e_commerce.backend.response.ApiResponse;
import com.example.e_commerce.backend.services.CartService;
import com.example.e_commerce.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt )throws UserException{
        // Implement later
        return null;
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItem(@RequestBody AddItems request, @RequestHeader("Authorization")
    String jwt){

        // To be implemented Later
        return null;
    }
}
