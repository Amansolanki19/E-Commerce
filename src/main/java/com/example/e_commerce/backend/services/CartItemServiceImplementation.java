package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.CartItemException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.CartItemRepo;
import com.example.e_commerce.backend.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem updateCart(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=cartItemRepo.findById((id)).orElseThrow(()->new CartItemException("No item found by id: "+id));
        User user=userService.findUserById(item.getId());
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }
        return cartItemRepo.save(item);
    }

    @Override
    public CartItem isExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepo.isItemExist(cart,product,size,userId);
    }

    @Override
    public void removeItems(Long userId, Long itemId) throws UserException, CartItemException {
        CartItem cartItem =findItemById(itemId);
        User user=userService.findUserById(cartItem.getId());
        cartItemRepo.deleteById(cartItem.getId());
    }

    @Override
    public CartItem findItemById(Long itemId) throws CartItemException {
        Optional<CartItem> find=cartItemRepo.findById(itemId);
        if(find.isPresent()){
            return find.get();
        }
        throw new CartItemException("No item found with id: "+itemId);
    }
}
