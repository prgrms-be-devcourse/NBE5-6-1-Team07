package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> findAll() {
        return productRepository.selectAll();
    }

    @Transactional
    public void insertProduct(ProductDto productDto) {
        try {
            productRepository.insert(productDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDto findById(Integer id) {
        return productRepository.selectById(id);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        try {
            productRepository.update(productDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
