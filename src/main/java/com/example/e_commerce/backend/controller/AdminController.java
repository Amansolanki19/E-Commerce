package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.exception.OrderException;
import com.example.e_commerce.backend.response.ApiResponse;
import com.example.e_commerce.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class AdminController {
    @Autowired
    private OrderService orderService;


    //To get all the placed order
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/confirmed")
    public ResponseEntity<Order> confirmedOrder(@PathVariable("id") Long orderId,
        @RequestHeader("Authorization") String jwt)throws OrderException{
        return ResponseEntity.ok(orderService.shippedOrder(orderId));
    }

    @PutMapping("/{id}/deliver")
    public ResponseEntity<Order> deliverOrder(@PathVariable("id") Long orderId,
                                              @RequestHeader("Authorization") String jwt)throws OrderException{
        return ResponseEntity.ok(orderService.deliverOrder(orderId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") Long orderId,
                                             @RequestHeader("Authorization") String jwt)throws OrderException{
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("id") Long orderId,
                                             @RequestHeader("Authorization") String jwt)throws OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Order deleted successfully");
        apiResponse.setStatus(true);
        return ResponseEntity.ok(apiResponse);
    }

}
