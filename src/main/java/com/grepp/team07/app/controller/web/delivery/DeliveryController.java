package com.grepp.team07.app.controller.web.delivery;

import com.grepp.team07.app.model.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("send")
    public ResponseEntity<String> sendProduct(
            @RequestParam
            Integer orderId
    ){
        deliveryService.sendProduct(orderId);
        return ResponseEntity.ok("상품이 발송되었습니다.");
    }
}
