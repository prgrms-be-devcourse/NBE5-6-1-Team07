package com.grepp.team07.app.model.product;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@Slf4j
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void bestProduct(){
        List<ProductDto> products = productService.bestProduct();
        products.forEach(System.out::println);
    }
}