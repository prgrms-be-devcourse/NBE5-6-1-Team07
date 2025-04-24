package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByKeyword(keyword);
    }
}
