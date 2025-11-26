package com.example.e_commerce.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address= new ArrayList<>();

    @Embedded
    @ElementCollection
    @CollectionTable(name = "paymentinfo_table",joinColumns = @JoinColumn(name = "user_id"))
    private List<Payments> paymentsList= new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ratings> ratingList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviewsList = new ArrayList<>();

    private LocalDateTime createdAt;

}
