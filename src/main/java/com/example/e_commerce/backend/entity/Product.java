package com.example.e_commerce.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private String description;
    @Column(precision = 2,nullable = false)
    private Double price;
    @Column(precision = 2,nullable = false)
    private Double discountedPrice;
    @Column(precision = 2,nullable = false)
    private Double discount;
    @Column(nullable = false)
    private Integer quantity;
    private String brand;
    private String color;
    private LocalDateTime createdAt;

    @Embedded
    @ElementCollection
    @Column(name = "size",nullable = true)
    private Set<Size> sizes= new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ratings>ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_rating")
    private Integer productRating;


}
