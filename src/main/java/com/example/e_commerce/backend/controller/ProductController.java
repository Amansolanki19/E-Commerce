package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.Size;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategory(@RequestParam String category,
                                                               @RequestParam List<String> color,
                                                               @RequestParam List<Size> size,
                                                               @RequestParam Integer minPrice,
                                                               @RequestParam Integer maxPrice,
                                                               @RequestParam Double minDiscount,
                                                               @RequestParam String sort,
                                                               @RequestParam String stock,
                                                               @RequestParam Integer pageNumber,
                                                               @RequestParam Integer pageSize){
        Page<Product> products = productService.filterAllProduct(category,color,size,minPrice,maxPrice,minDiscount
        ,sort,stock,pageNumber,pageSize);

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId)throws ProductException {
        return ResponseEntity.ok(productService.findProductById(productId));
    }
}
