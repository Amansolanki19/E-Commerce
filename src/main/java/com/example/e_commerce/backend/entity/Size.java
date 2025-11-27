package com.example.e_commerce.backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private String name;
    private Integer quantity;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Size(String name) {
        this.name = name;
    }
}
