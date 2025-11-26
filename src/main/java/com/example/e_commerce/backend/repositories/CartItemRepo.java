package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.Cart;
import com.example.e_commerce.backend.entity.CartItem;
import com.example.e_commerce.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {
    @Query("SELECT c FROM CartItem c WHERE c.cart = :cart AND c.product = :product AND c.size = :size AND c.userId = :userId")
    CartItem isItemExist(@Param("cart") Cart cart, @Param("product")Product product,@Param("size") String size,@Param("userId") Long userId);


}
