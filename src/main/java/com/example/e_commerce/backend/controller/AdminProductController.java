package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.request.CreateProduct;
import com.example.e_commerce.backend.response.ApiResponse;
import com.example.e_commerce.backend.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProduct request){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id")Long productId)throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Product deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable("id") Long productId) throws ProductException{
        return ResponseEntity.ok(productService.updateProduct(productId,product));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ApiResponse> createProduct(@RequestBody CreateProduct[] request){
        for(CreateProduct create: request){
            productService.createProduct(create);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Products added successfully");
        apiResponse.setStatus(true);
        return ResponseEntity.ok(apiResponse);
    }
}
