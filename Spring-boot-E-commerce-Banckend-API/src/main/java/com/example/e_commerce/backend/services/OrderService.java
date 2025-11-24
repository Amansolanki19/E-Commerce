package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Address;
import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.OrderException;

import java.util.List;

public interface OrderService {
     Order createOrder(User user, Address deliveryAddress);
     Order findOrderById(Long orderId)throws OrderException;
     List<Order> orderHistory(Long userId);
     Order placedOrder(Long orderId)throws OrderException;
     Order confirmOrder(Long orderId)throws OrderException;
     Order shippedOrder(Long orderId)throws OrderException;
     Order deliverOrder(Long orderId)throws OrderException;
     Order cancelOrder(Long orderId)throws OrderException;
     List<Order>getAllOrders();
     void deleteOrder(Long orderId) throws OrderException;
}
