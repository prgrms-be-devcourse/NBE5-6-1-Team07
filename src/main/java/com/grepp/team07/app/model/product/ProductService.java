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

    public List<ProductDto> findAll() {
        return productRepository.selectAll();
    }

    @Transactional
    public void insertProduct(List<MultipartFile> thumbnail, ProductDto productDto) {
        try {
            List<FileDto> fileDtos = fileUtil.upload(thumbnail, "product");
            productRepository.insert(productDto);

            if(fileDtos.isEmpty()) return;

            Integer productId = productDto.getProductId();
            ProductImgDto productImgDto = new ProductImgDto(productId, fileDtos.get(0));
            productRepository.insertImage(productImgDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDto findById(Integer id) {
        return productRepository.selectById(id);
    }

    @Transactional
    public void updateProduct(List<MultipartFile> thumbnail, ProductDto productDto) {
        try {
            List<FileDto> fileDtos = fileUtil.upload(thumbnail, "product");
            productRepository.update(productDto);

            Integer productId = productDto.getProductId();
            productRepository.deleteImage(productId);

            if(fileDtos.isEmpty()) return;

            ProductImgDto productImgDto = new ProductImgDto(productId, fileDtos.get(0));
            productRepository.insertImage(productImgDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void incrementCount(Integer productId) {
        productRepository.increment(productId);
    }

    @Transactional
    public void decrementCount(Integer productId) {
        productRepository.decrement(productId);
    }

    @Transactional
    public void delete(Integer productId) {
        productRepository.deleteImage(productId);
        productRepository.delete(productId);
    }
}
