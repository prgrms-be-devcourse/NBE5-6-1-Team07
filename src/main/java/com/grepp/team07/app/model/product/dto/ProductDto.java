package com.grepp.team07.app.model.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String image;
    private String price;
    private String info;
    private String brand;
    private Integer count = 10;
    private Integer sales = 0;


    public ProductDto(String name, String price, String info, String brand) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.brand = brand;
    }
}
