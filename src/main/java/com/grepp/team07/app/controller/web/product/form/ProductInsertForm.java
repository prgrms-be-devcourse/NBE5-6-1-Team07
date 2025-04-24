package com.grepp.team07.app.controller.web.product.form;

import com.grepp.team07.app.model.product.dto.ProductDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductInsertForm {

    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private String price;
    @NotBlank
    private String info;
    @NotBlank
    private String brand;

    public ProductDto toDto() {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setImage(image);
        productDto.setPrice(price);
        productDto.setInfo(info);
        productDto.setBrand(brand);
        return productDto;
    }
}
