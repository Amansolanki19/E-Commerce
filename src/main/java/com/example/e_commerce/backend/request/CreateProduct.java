package com.example.e_commerce.backend.request;

import com.example.e_commerce.backend.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProduct {

    private String title;
    private String description;
    private Double price;
    private Double discountedPrice;
    private Double discount;
    private Integer quantity;
    private String brand;
    private String color;
    private Set<Size> size = new HashSet<>();
    private String topLevelCategory;
    private String midLevelCategory;
    private String lowLevelCategory;
}
