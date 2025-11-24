package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.exception.CartItemException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.CartItemRepo;
import com.example.e_commerce.backend.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImplementation implements CartItemService{

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public CartItem createCart(CartItem cartItem) {
        cartItem.setQuantity();
        return null;
    }

    @Override
    public CartItem updateCart(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        return null;
    }

    @Override
    public CartItem isExist(Cart cart, Product product, String size, Long userId) {
        return null;
    }

    @Override
    public void removeItems(Long userId, Long itemId) throws UserException, CartItemException {

    }

    @Override
    public CartItem findItemById(Long itemId) throws CartItemException {
        return null;
    }
}
