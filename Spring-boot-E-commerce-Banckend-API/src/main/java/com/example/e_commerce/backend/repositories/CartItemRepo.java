package com.example.e_commerce.backend.repositories;

import com.example.e_commerce.backend.entity.CartItem;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface CartItemRepo extends JpaAttributeConverter<CartItem,Long> {
}
