package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {

    List<ProductDto> selectAll();

    @Insert("insert into product(name, image, price, info, brand)"
        + "values (#{name}, #{image}, #{price}, #{info}, #{brand})")
    void insert(ProductDto productDto);
}
