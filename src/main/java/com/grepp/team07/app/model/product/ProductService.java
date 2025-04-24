package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> searchProducts(String keyword) {
        return productRepository.findByKeyword(keyword);
    }

    public List<ProductDto> bestProduct() {
        return productRepository.bestProduct();
    }

    public List<ProductDto> selectAll() {
        return productRepository.selectAll();
    }

    public Page<ProductDto> findPaged(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<ProductDto> result = productRepository.findPaged(size, offset);
        int total = productRepository.countAll();

        return new PageImpl<>(result, pageable, total);
    }
}
