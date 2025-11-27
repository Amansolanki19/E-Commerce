package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.entity.Address;
import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.exception.OrderException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.services.OrderService;
import com.example.e_commerce.backend.services.UserService;
import com.example.e_commerce.backend.Configuration.JwtProvider;
import com.example.e_commerce.backend.entity.User;
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

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Address address, @RequestHeader("Authorization") String jwt){
        String email = jwtProvider.getEmail(jwt);
        try{
            User user = userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
            Order order = orderService.createOrder(user, address);
            return ResponseEntity.ok(order);
        }catch(UserException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization")String jwt)throws UserException {

        String email = jwtProvider.getEmail(jwt);
        User user = userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        List<Order> orders = orderService.orderHistory(user.getId());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id")Long orderId,@RequestHeader("Authorization")String jwt)
            throws UserException, OrderException {

        String email = jwtProvider.getEmail(jwt);
        userService.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        Order order = orderService.findOrderById(orderId);
        return ResponseEntity.ok(order);
    }

}
