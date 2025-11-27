package com.example.e_commerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)

    private List<OrderItems> orderItemsList = new ArrayList<>();

    private LocalDateTime orderDate;

    private LocalDateTime deliverDate;

    @OneToOne
    private Address deliveryAddress;

    @Embedded
    private PaymentDetail paymentDetail= new PaymentDetail();

    private Double totalPrice;
    private Double discountedPrice;
    private Double discount;

    private String orderStatus;

    private Integer totalItems;

    private LocalDateTime orderPlacedDate;
}

