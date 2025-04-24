package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductRepository {

    List<ProductDto> selectAll();

    ProductDto selectById(Integer id);

    @Insert("insert into product(name, image, price, info, brand)"
        + "values (#{name}, #{image}, #{price}, #{info}, #{brand})")
    void insert(ProductDto productDto);

    @Update("update product set name=#{name}, image=#{image}, price=#{price}, info=#{info} where product_id=#{id}")
    boolean update(ProductDto productDto);
}
