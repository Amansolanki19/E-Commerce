package com.example.e_commerce.backend.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "payment_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetail {
    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;

}
