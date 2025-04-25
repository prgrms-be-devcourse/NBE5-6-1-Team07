package com.grepp.team07.app.model.admin;

import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.app.model.product.dto.ProductImgDto;
import com.grepp.team07.infra.util.file.FileDto;
import com.grepp.team07.infra.util.file.FileUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final FileUtil fileUtil;

    public List<ProductDto> findAll() {
        return adminRepository.selectAll();
    }

    public List<ProductDto> searchByKeyword(String keyword) {
        return adminRepository.searchByKeyword(keyword);
    }

    @Transactional
    public void insertProduct(List<MultipartFile> thumbnail, ProductDto productDto) {
        try {
            List<FileDto> fileDtos = fileUtil.upload(thumbnail, "product");
            adminRepository.insert(productDto);

            if(fileDtos.isEmpty()) return;

            Integer productId = productDto.getProductId();
            ProductImgDto productImgDto = new ProductImgDto(productId, fileDtos.get(0));
            adminRepository.insertImage(productImgDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDto findById(Integer id) {
        return adminRepository.selectById(id);
    }

    @Transactional
    public void updateProduct(List<MultipartFile> thumbnail, ProductDto productDto) {
        try {
            List<FileDto> fileDtos = fileUtil.upload(thumbnail, "product");
            adminRepository.update(productDto);

            Integer productId = productDto.getProductId();
            adminRepository.deleteImage(productId);

            if(fileDtos.isEmpty()) return;

            ProductImgDto productImgDto = new ProductImgDto(productId, fileDtos.get(0));
            adminRepository.insertImage(productImgDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void incrementCount(Integer productId) {
        adminRepository.increment(productId);
    }

    @Transactional
    public void decrementCount(Integer productId) {
        adminRepository.decrement(productId);
    }

    @Transactional
    public void delete(Integer productId) {
        adminRepository.deleteImage(productId);
        adminRepository.delete(productId);
    }
}

