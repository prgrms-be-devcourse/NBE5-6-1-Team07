package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.delivery.code.DeliveryState;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderedRepository {

    List<OrderedDto> selectAll();

    int countAllOrders();

    List<OrderedDto> findPaged(@Param("limit") int limit, @Param("offset") int offset);

    List<OrderedDto> searchByEmail(@Param("limit") int limit, @Param("offset") int offset, String email);
    int countEmailOrders(String email);

    List<OrderedDto> searchByMemberEmail(@Param("limit") int limit, @Param("offset") int offset, String email);
    int countMemberEmailOrders(String email);

    // 회원 주문 생성
    void insertMemberOrder(@Param("email") String email,
                           @Param("address") String address,
                           @Param("postCode") String postCode,
                           @Param("userId") String userId);

    // 비회원 주문 생성
    void insertGuestOrder(@Param("email") String email,
                          @Param("address") String address,
                          @Param("postCode") String postCode);

    // 생성된 orderId 가져오기
    Integer findLastOrderId();

    // 상품 가격 가져오기
    Integer findProductPrice(@Param("productId") Integer productId);

    // 주문 상품 추가
    void insertOrderProducts(@Param("orderId") Integer orderId,
                             @Param("productId") Integer productId,
                             @Param("count") Integer count);

    // 총 가격 업데이트
    void updateOrderTotalPrice(@Param("orderId") Integer orderId,
                               @Param("totalPrice") Integer totalPrice);

    List<Map<String, Object>> findCartProductByUserId(@Param("userId") String userId);

    Integer findCustomerIdByUserId(@Param("userId") String userId);
}
