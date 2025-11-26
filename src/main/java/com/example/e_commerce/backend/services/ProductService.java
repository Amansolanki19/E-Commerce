package com.example.e_commerce.backend.services;

import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.Size;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.request.CreateProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
     Product createProduct(CreateProduct product);

     Product deleteProduct(Long productId)throws ProductException;

     Product updateProduct(Long productId,Product product)throws  ProductException;

     Product findProductById(Long productId)throws ProductException;

     List<Product> findAllProductCategory();



    Page<Product> filterAllProduct(String category, List<String> colors, List<Size> sizes,
                                   Integer maxPrice, Integer minPrice, Double minDiscount,
                                   String sort, String stock, Integer pageNumber, Integer pageSize);

    List<Product> findAll();
}
