package com.grepp.team07.app.model.product;

import com.grepp.team07.app.model.product.dto.ProductDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductRepository {

    List<ProductDto> selectAll();

    ProductDto selectById(Integer id);

    @Insert("insert into product(name, image, price, info, brand)"
        + "values (#{name}, #{image}, #{price}, #{info}, #{brand})")
    void insert(ProductDto productDto);

    @Update("update product set name=#{name}, image=#{image}, price=#{price}, info=#{info}, brand=#{brand} where product_id=#{productId}")
    boolean update(ProductDto productDto);

    @Update("UPDATE product SET count = count + 1 WHERE product_id = #{productId}")
    void increment(@Param("productId") Integer productId);

    @Update("UPDATE product SET count = count - 1 WHERE product_id = #{productId} AND count > 0")
    void decrement(@Param("productId") Integer productId);
}
