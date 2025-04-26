package com.grepp.team07.app.model.cart;

import com.grepp.team07.app.model.cart.dto.CartDto;
import com.grepp.team07.app.model.cart.dto.CartProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartRepository {

    Optional<CartDto> findActiveCart(@Param("userId") String userId);

    void createCart(@Param("userId") String userId);

    void deactivateCart(@Param("userId") String userId);

    CartProductDto getProduct(@Param("cartId") int cartId, @Param("productId") int productId);

    void addProduct(@Param("cartId") int cartId, @Param("productId") int productId, @Param("count") int count);

    void updateCount(@Param("cartProductId") int cartProductId, @Param("count") int count);

    List<CartProductDto> getProducts(@Param("userId") String userId);
}
