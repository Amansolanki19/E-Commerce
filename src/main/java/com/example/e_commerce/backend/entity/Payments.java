package com.example.e_commerce.backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paymentInfo")
@Getter
@Setter
public class Payments {

    @NotBlank
    private String cardHolderName;

    @Column(name = "card_number",nullable = false,length = 12)
    private Integer cardNumber;

    @Column(name = "expiry")
    @NotBlank
    private String expiry;

    @Column(name = "cvv", length = 3)
    private Short cvv;
}
