package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.exception.CartItemException;
import com.example.e_commerce.backend.exception.UserException;

public interface CartItemService {
    CartItem createCart(CartItem cartItem);
    CartItem updateCart(Long userId,Long id,CartItem cartItem)throws CartItemException, UserException;
    CartItem isExist(Cart cart, Product product,String size,Long userId);
    void removeItems(Long userId,Long itemId)throws UserException,CartItemException;
    CartItem findItemById(Long itemId)throws CartItemException;

}
