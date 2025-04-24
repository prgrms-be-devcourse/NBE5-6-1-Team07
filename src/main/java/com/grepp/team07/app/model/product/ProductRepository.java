package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {

    List<Product> findByKeyword(String keyword);
}
