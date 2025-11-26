package com.example.e_commerce.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "local", nullable = false)
    private String localAddress;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "pinCode",nullable = false,length = 6)
    private Integer pinCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "mobile", length = 10)
    private Integer mobile;
}
