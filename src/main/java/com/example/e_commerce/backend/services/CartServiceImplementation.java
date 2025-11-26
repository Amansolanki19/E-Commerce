package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.CartRepo;
import com.example.e_commerce.backend.request.AddItems;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class CartServiceImplementation implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;


    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return  cartRepo.save(cart);
    }

    @Override
    public String addItem(Long userId, AddItems addItems) throws ProductException {
        Cart cart=cartRepo.findById(userId).orElseThrow();
        Product product=productService.findProductById(addItems.getProductId());
        CartItem isPresent=cartItemService.isExist(cart,product,addItems.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(addItems.getQuantity());
            cartItem.setUserId(userId);

            Double updatedPrice=addItems.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(updatedPrice);
            cartItem.setSize(addItems.getSize());

            CartItem cartItem1= cartItemService.createCart(cartItem);
            cart.getCartItems().add(cartItem1);
        }
        else{
            // Add later
        }
        return "Item added successfully";
    }


}
