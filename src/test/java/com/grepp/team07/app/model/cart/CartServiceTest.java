package com.grepp.team07.app.model.cart;

import com.grepp.team07.app.model.cart.dto.CartProductDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    public void addProduct() {
        HttpSession session = new MockHttpSession();
        // 비회원 테스트
        cartService.addProduct(1, 2, session, null);
        // 회원 테스트
        cartService.addProduct(2, 3, session, "user01");
    }

    @Test
    public void getCartItems() {
        HttpSession session = new MockHttpSession();
        // 비회원 장바구니 상품 추가
        cartService.addProduct(1, 2, session, null);

        // 비회원
        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        guestItems.forEach(item -> log.info("비회원 장바구니: {}", item));

        // 회원
        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        memberItems.forEach(item -> log.info("회원 장바구니: {}", item));
    }

    @Test
    public void clearCart() {
        HttpSession session = new MockHttpSession();
        // 비회원 장바구니 상품 추가
        cartService.addProduct(1, 2, session, null);

        // 비회원
        cartService.clearCart(session, null);
        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        log.info("비운 후 비회원 장바구니: {}", guestItems);

        // 회원
        cartService.clearCart(session, "user01");
        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        log.info("비운 후 회원 장바구니: {}", memberItems);
    }
}