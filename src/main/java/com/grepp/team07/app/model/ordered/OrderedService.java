package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.cart.CartRepository;
import com.grepp.team07.app.model.delivery.DeliveryRepository;
import com.grepp.team07.app.model.delivery.code.DeliveryState;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderedService {

    private final OrderedRepository orderedRepository;
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;

    public List<OrderedDto> findAll() {
        return orderedRepository.selectAll();
    }

    public Page<OrderedDto> findPaged(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result =  orderedRepository.findPaged(size, offset);
        int total = orderedRepository.countAllOrders();

        return new PageImpl<>(result, pageable, total);
    }

    public Page<OrderedDto> searchByEmail(Pageable pageable, String email) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result = orderedRepository.searchByEmail(size, offset, email);
        int total = orderedRepository.countEmailOrders(email);

        return new PageImpl<>(result, pageable, total);
    }

    public Page<OrderedDto> searchByMemberEmail(Pageable pageable, String email) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result =  orderedRepository.searchByMemberEmail(size, offset, email);
        int total = orderedRepository.countMemberEmailOrders(email);

        return new PageImpl<>(result, pageable, total);
    }

    public void create(HttpSession session, String email, String address, String postCode, String userId) {
        if (email == null || email.isBlank() || address == null || address.isBlank() || postCode == null || postCode.isBlank()) {
            throw new IllegalArgumentException("이메일, 주소, 우편번호를 모두 입력해야 합니다.");
        }

        if (userId != null) {
            // 회원
            orderedRepository.insertMemberOrder(email, address, postCode, userId);
            Integer orderId = orderedRepository.findLastOrderId();

            Integer customerId = orderedRepository.findCustomerIdByUserId(userId);
            deliveryRepository.insertDelivery(orderId, customerId, DeliveryState.READY);

            List<Map<String, Object>> cartProducts = orderedRepository.findCartProductByUserId(userId);

            for (Map<String, Object> item : cartProducts) {
                Integer productId = (Integer) item.get("product_id");
                Integer count = (Integer) item.get("count");
                orderedRepository.insertOrderProducts(orderId, productId, count);
            }

            cartRepository.deactivateCart(userId);
        } else {
            // 비회원
            Map<Integer, Integer> guestCart = (Map<Integer, Integer>) session.getAttribute("guestCart");
            if (guestCart == null || guestCart.isEmpty()) {
                throw new IllegalArgumentException("장바구니가 비어있습니다.");
            }

            orderedRepository.insertGuestOrder(email, address, postCode);

            Integer orderId = orderedRepository.findLastOrderId();

            deliveryRepository.insertDelivery(orderId, null, DeliveryState.READY);

            int totalPrice = 0;
            for (Map.Entry<Integer, Integer> entry : guestCart.entrySet()) {
                Integer productId = entry.getKey();
                Integer count = entry.getValue();
                Integer price = orderedRepository.findProductPrice(productId);
                totalPrice += price * count;
                orderedRepository.insertOrderProducts(orderId, productId, count);
            }

            orderedRepository.updateOrderTotalPrice(orderId, totalPrice);

            session.removeAttribute("guestCart");
        }
    }
}
