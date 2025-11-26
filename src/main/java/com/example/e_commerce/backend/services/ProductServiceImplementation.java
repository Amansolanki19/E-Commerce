package com.example.e_commerce.backend.services;


import com.example.e_commerce.backend.entity.Category;
import com.example.e_commerce.backend.entity.Product;
import com.example.e_commerce.backend.entity.Size;
import com.example.e_commerce.backend.exception.ProductException;
import com.example.e_commerce.backend.repositories.CategoryRepo;
import com.example.e_commerce.backend.repositories.ProductRepo;
import com.example.e_commerce.backend.request.CreateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Product createProduct(CreateProduct product) {
        Category topLevel = categoryRepo.findByName(product.getTopLevelCategory());
        if(topLevel==null){
            Category topLevelCategory=new Category();
            topLevelCategory.setName(product.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel=categoryRepo.save(topLevelCategory);
        }

        Category secondLevel=categoryRepo.findByNameAndParent(product.getMidLevelCategory(),topLevel.getName());
        if(secondLevel==null){
            Category secondLevelCategory= new Category();
            secondLevelCategory.setName(product.getMidLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);
            secondLevel=categoryRepo.save(secondLevel);
        }

        Category thirdLevel=categoryRepo.findByNameAndParent(product.getLowLevelCategory(),secondLevel.getName());
        if(thirdLevel==null){
            Category thirLevelCategory=new Category();
            thirLevelCategory.setName(product.getLowLevelCategory());
            thirLevelCategory.setParentCategory(secondLevel);
            thirLevelCategory.setLevel(3);

            thirLevelCategory=categoryRepo.save(thirLevelCategory);
        }

        Product product1 = new Product();
        product1.setTitle(product.getTitle());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());
        product1.setDiscount(product.getDiscount());
        product1.setDiscountedPrice(product.getDiscountedPrice());
        product1.setBrand(product.getBrand());
        product1.setPrice(product.getPrice());
        product1.setSizes(product.getSize());
        product1.setQuantity(product.getQuantity());
        product1.setCategory(thirdLevel);
        product1.setCreatedAt(LocalDateTime.now());


        return productRepo.save(product1);
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductException {
         Product product = findProductById(productId);
         product.getSizes().clear();
         productRepo.delete(product);
         return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        Product product1 = findProductById(productId);
        if(product.getQuantity()!=0){
            product1.setQuantity(product.getQuantity());
        }

        //Left fields will update later

        return productRepo.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepo.findById(productId).orElseThrow(
                ()->new RuntimeException("No product found with id: "+productId));
    }

    @Override
    public List<Product> findAllProductCategory() {
        return List.of();
    }


    @Override
    public Page<Product> filterAllProduct(String category, List<String> colors, List<Size> sizes,
                                          Integer maxPrice, Integer minPrice, Double minDiscount,
                                          String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Product> products = productRepo.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        if(!colors.isEmpty()){
            products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase
                    (p.getColor()))).collect(Collectors.toList());
        }
        if(stock!=null){
            if (stock.equals("in_stock"))   products=products.stream().filter(p->p.getQuantity()>0)
                    .collect(Collectors.toList());
        } else if (stock.equals("out_of_stock")) {
            products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
        }
        int startIndex=(int)pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());

        List<Product> pageContent=products.subList(startIndex,endIndex);

        Page<Product> filter= new PageImpl<>(pageContent,pageable, products.size());
        return filter;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
