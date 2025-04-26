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
        cartService.addProduct(1, 2, session, null);
        cartService.increaseCount(1, session, null);

        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        guestItems.forEach(item -> log.info("비회원 장바구니: {}", item));

        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        memberItems.forEach(item -> log.info("회원 장바구니: {}", item));
    }

    @Test
    public void clearCart() {
        HttpSession session = new MockHttpSession();
        cartService.addProduct(1, 2, session, null);
        cartService.increaseCount(1, session, null);

        cartService.clearCart(session, null);
        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        log.info("비운 후 비회원 장바구니: {}", guestItems);

        cartService.clearCart(session, "user01");
        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        log.info("비운 후 회원 장바구니: {}", memberItems);
    }

    @Test
    public void increaseCount() {
        HttpSession session = new MockHttpSession();
        cartService.addProduct(1, 1, session, null);
        cartService.increaseCount(1, session, null);

        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        guestItems.forEach(item -> log.info("비회원 수량 증가: {}", item));

        cartService.addProduct(2, 1, session, "user01");
        cartService.increaseCount(2, session, "user01");

        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        memberItems.forEach(item -> log.info("회원 수량 증가: {}", item));
    }

    @Test
    public void decreaseCount() {
        HttpSession session = new MockHttpSession();
        cartService.addProduct(1, 2, session, null);
        cartService.decreaseCount(1, session, null);

        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        guestItems.forEach(item -> log.info("비회원 수량 감소: {}", item));

        cartService.addProduct(2, 2, session, "user01");
        cartService.decreaseCount(2, session, "user01");

        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        memberItems.forEach(item -> log.info("회원 수량 감소: {}", item));
    }

    @Test
    public void removeProduct() {
        HttpSession session = new MockHttpSession();
        cartService.addProduct(1, 1, session, null);
        cartService.removeProduct(1, session, null);

        List<CartProductDto> guestItems = cartService.getCartItems(session, null);
        log.info("비회원 삭제 후 장바구니: {}", guestItems);

        cartService.addProduct(2, 1, session, "user01");
        cartService.removeProduct(2, session, "user01");

        List<CartProductDto> memberItems = cartService.getCartItems(session, "user01");
        log.info("회원 삭제 후 장바구니: {}", memberItems);
    }
}
