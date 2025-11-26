package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.entity.Address;
import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.exception.OrderException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.services.OrderService;
import com.example.e_commerce.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Address address, @RequestHeader("Authorization") String jwt){
        // Implement later
        return null;
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization")String jwt)throws UserException {

        // Implement later
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id")Long orderId,@RequestHeader("Authorization")String jwt)
            throws UserException, OrderException {

        //To be implemented
        return null;
    }

}
