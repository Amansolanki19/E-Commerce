package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.CartRepo;
import com.example.e_commerce.backend.request.AddItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {
    @Autowired
    private CartRepo cartRepo;
    private CartItemService cartItemService;
    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addItem(Long userId, AddItems addItems) throws ProductException {
        return "";
    }

    @Override
    public Cart findUserCart(Long userId) throws UserException {
        return null;
    }
}
