package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@Slf4j
class OrderedServiceTest {
    @Autowired
    private OrderedService orderedService;

    @Test
    public void selectAll(){
        List<OrderedDto> orders = orderedService.findAll();
        orders.forEach(System.out::println);
    }

    @Test
    public void createGuestOrderTest() {
        // 비회원 주문 테스트
        MockHttpSession session = new MockHttpSession();
        Map<Integer, Integer> guestCart = new HashMap<>();
        guestCart.put(1, 2);
        guestCart.put(2, 1);
        session.setAttribute("guestCart", guestCart);

        String email = "guest@example.com";
        String address = "비회원주소";
        String postCode = "12345";

        assertDoesNotThrow(() -> orderedService.create(session, email, address, postCode, null));

        log.info("비회원 주문 생성 완료");
    }

    @Test
    public void createMemberOrderTest() {
        // 회원 주문 테스트
        MockHttpSession session = new MockHttpSession();

        String email = "member@example.com";
        String address = "회원주소";
        String postCode = "67890";
        String userId = "user01";

        assertDoesNotThrow(() -> orderedService.create(session, email, address, postCode, userId));

        log.info("회원 주문 생성 완료");
    }
}
