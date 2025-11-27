package com.example.e_commerce.backend.services;


import com.example.e_commerce.backend.entity.Address;
import com.example.e_commerce.backend.entity.Order;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.OrderException;
import com.example.e_commerce.backend.repositories.CartRepo;
import com.example.e_commerce.backend.repositories.OrderItemsRepo;
import com.example.e_commerce.backend.repositories.OrderRepo;
import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.OrderItems;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
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
    @Autowired
    private CartService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Override
    public Order createOrder(User user, Address deliveryAddress) {
        Cart cart = cartRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setOrderId("ORD-" + System.currentTimeMillis());
        order.setUser(user);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");

        double totalPrice = 0d;
        int totalItems = 0;
        List<OrderItems> items = new ArrayList<>();

        for (CartItem ci : cart.getCartItems()) {
            OrderItems oi = new OrderItems();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getPrice());
            oi.setDiscountedPrice(ci.getDiscountedPrice());
            oi.setSize(ci.getSize());
            oi.setUserId(ci.getUserId());
            oi.setDeliveryDate(null);
            oi.setOrder(order);
            items.add(oi);

            totalPrice += ci.getPrice() == null ? 0 : ci.getPrice();
            totalItems += ci.getQuantity() == null ? 0 : ci.getQuantity();
        }

        order.setOrderItemsList(items);
        order.setTotalPrice(totalPrice);
        order.setTotalItems(totalItems);
        order.setOrderPlacedDate(LocalDateTime.now());

        Order saved = orderRepo.save(order);
        // orderItems will be saved by cascade, but ensure repository sync
        return saved;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
    }

    @Override
    public List<Order> orderHistory(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderStatus("PLACED");
        order.setOrderPlacedDate(LocalDateTime.now());
        return orderRepo.save(order);
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderStatus("CONFIRMED");
        return orderRepo.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderStatus("SHIPPED");
        return orderRepo.save(order);
    }

    @Override
    public Order deliverOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderStatus("DELIVERED");
        order.setDeliverDate(LocalDateTime.now());
        return orderRepo.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderStatus("CANCELLED");
        return orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        orderRepo.delete(order);
    }
}
