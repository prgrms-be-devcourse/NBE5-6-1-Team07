package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.app.model.product.dto.ProductImgDto;
import com.grepp.team07.infra.util.file.FileDto;
import com.grepp.team07.infra.util.file.FileUtil;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final FileUtil fileUtil;

    public List<ProductDto> searchProducts(String keyword) {
        return productRepository.findByKeyword(keyword);
    }

    public List<ProductDto> bestProduct() {
        return productRepository.bestProduct();
    }
}
