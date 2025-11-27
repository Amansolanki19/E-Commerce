package com.example.e_commerce.backend.controller;


import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.request.AddItems;
import com.example.e_commerce.backend.response.ApiResponse;
import com.example.e_commerce.backend.services.CartService;
import com.example.e_commerce.backend.services.UserService;
import com.example.e_commerce.backend.Configuration.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt )throws UserException{
        String email = jwtProvider.getEmail(jwt);
        com.example.e_commerce.backend.entity.User user = userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        Cart cart = cartService.createCart(user);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItem(@RequestBody AddItems request, @RequestHeader("Authorization")
    String jwt){

        // To be implemented Later
        String email = jwtProvider.getEmail(jwt);
        try{
            com.example.e_commerce.backend.entity.User user = userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
            String message = cartService.addItem(user.getId(), request);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus(true);
            apiResponse.setMessage(message);
            return ResponseEntity.ok(apiResponse);
        }catch(UserException | com.example.e_commerce.backend.exception.ProductException e){
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus(false);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
