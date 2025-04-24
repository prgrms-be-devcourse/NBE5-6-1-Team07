package com.grepp.team07.app.model.product;

import static org.junit.jupiter.api.Assertions.*;

import com.grepp.team07.app.model.product.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@Slf4j
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void selectAll() {
        log.info("all products : {}", productRepository.selectAll());
    }

    @Test
    public void selectById() {
        log.info("product by id : {}", productRepository.selectById(1));
    }

    @Test
    public void update() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(7);
        productDto.setName("test");
        productDto.setPrice("test");
        productDto.setInfo("test");
        productDto.setBrand("test");
        productRepository.update(productDto);
        log.info("update by id : {}", productRepository.update(productDto));
    }
  
}