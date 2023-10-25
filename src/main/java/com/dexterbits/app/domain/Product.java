package com.dexterbits.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer productId;

    private String name;

    private Integer CategoryId;

    private Double price;

    private Integer stock;

    private Boolean active;

    private Category category;

}
