package com.example.e_commerce.backend.services;


import com.example.e_commerce.backend.entity.Address;
import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.OrderException;
import com.example.e_commerce.backend.repositories.CartRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceImplementation implements OrderService{

    private CartRepo cartRepo;
    private CartService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Order createOrder(User user, Address deliveryAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> orderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliverOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
