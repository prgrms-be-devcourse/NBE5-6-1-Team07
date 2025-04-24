package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {

    List<ProductDto> findByKeyword(String keyword);

    List<ProductDto> bestProduct();

    List<ProductDto> selectAll();
}
