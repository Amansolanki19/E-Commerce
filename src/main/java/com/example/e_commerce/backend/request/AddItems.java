package com.example.e_commerce.backend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddItems {
    private Long productId;
    private String size;
    private Integer quantity;
    private Double price;
}
