package com.grepp.team07.app.controller.web.admin.form;

import com.grepp.team07.app.model.product.dto.ProductDto;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductInsertForm {

    private List<MultipartFile> thumbnail;
    private Integer productId;
    @NotBlank
    private String name;
    @NotBlank
    private String price;
    @NotBlank
    private String info;
    @NotBlank
    private String brand;

    public ProductDto toDto() {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setInfo(info);
        productDto.setBrand(brand);
        return productDto;
    }

    public ProductDto fromForm() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(this.getProductId());
        productDto.setName(this.getName());
        productDto.setPrice(this.getPrice());
        productDto.setInfo(this.getInfo());
        productDto.setBrand(this.getBrand());
        return productDto;
    }
}
