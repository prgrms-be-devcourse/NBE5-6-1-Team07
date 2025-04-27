package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void selectAll() {
        List<ProductDto> products = productService.selectAll();
        products.forEach(product -> log.info("전체 상품: {}", product));
    }

    @Test
    public void searchProducts() {
        List<ProductDto> products = productService.searchProducts("콜드브루");
        products.forEach(product -> log.info("검색된 상품: {}", product));
    }

    @Test
    public void findById() {
        ProductDto product = productService.findById(1);
        log.info("상품 조회: {}", product);
    }
}
