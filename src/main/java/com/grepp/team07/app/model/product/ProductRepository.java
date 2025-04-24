package com.grepp.team07.app.model.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductDto> bestProduct();
}
