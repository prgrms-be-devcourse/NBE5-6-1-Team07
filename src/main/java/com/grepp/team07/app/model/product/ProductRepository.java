package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.app.model.product.dto.ProductImgDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductRepository {

    List<ProductDto> findByKeyword(String keyword);

    List<ProductDto> bestProduct();
}
