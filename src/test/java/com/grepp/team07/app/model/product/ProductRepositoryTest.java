package com.grepp.team07.app.model.product;

import static org.junit.jupiter.api.Assertions.*;

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
  
}