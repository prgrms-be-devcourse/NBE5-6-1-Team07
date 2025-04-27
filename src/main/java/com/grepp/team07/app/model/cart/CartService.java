package com.grepp.team07.app.model.cart;

import com.grepp.team07.app.model.cart.dto.CartProductDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CartService {

    // 장바구니 상품 추가
    void addProduct(int productId, int count, HttpSession session, String userId);
    // 장바구니 목록 조회
    List<CartProductDto> getCartItems(HttpSession session, String userId);
    // 장바구니 비우기
    void clearCart(HttpSession session, String userId);
    // 수량 증가
    void increaseCount(int productId, HttpSession session, String userId);
    // 수량 감소
    void decreaseCount(int productId, HttpSession session, String userId);
    // 상품 삭제
    void removeProduct(int productId, HttpSession session, String userId);
}
