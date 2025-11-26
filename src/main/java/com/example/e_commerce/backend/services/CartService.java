package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.request.AddItems;

public interface CartService {

    Cart createCart(User user);
    String addItem(Long userId, AddItems addItems)throws ProductException;

}
