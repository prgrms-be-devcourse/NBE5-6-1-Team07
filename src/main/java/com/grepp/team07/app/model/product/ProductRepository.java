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

    List<ProductDto> selectAll();

    ProductDto selectById(Integer id);

    @Insert("insert into product(name, price, info, brand)"
        + "values (#{name}, #{price}, #{info}, #{brand})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    void insert(ProductDto productDto);

    @Select("insert into product_img (product_id, original_file_name, rename_file_name, save_path)"
        + "values (#{productId}, #{originalFileName}, #{renameFileName}, #{savePath})")
    void insertImage(ProductImgDto productImgDto);

    @Update("update product set name=#{name}, price=#{price}, info=#{info}, brand=#{brand} where product_id=#{productId}")
    boolean update(ProductDto productDto);

    @Update("UPDATE product SET count = count + 1 WHERE product_id = #{productId}")
    void increment(@Param("productId") Integer productId);

    @Update("UPDATE product SET count = count - 1 WHERE product_id = #{productId} AND count > 0")
    void decrement(@Param("productId") Integer productId);

    @Update("UPDATE product SET deleted_at = NOW() WHERE product_id = #{productId}")
    void delete(@Param("productId") Integer productId);

}
