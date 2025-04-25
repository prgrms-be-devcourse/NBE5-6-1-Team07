package com.grepp.team07.app.controller.web.delivery;

import com.grepp.team07.app.model.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("send/{orderId}")
    public String sendProduct(
            @PathVariable("orderId")
            Integer orderId
    ){
        log.info("orderId : ", orderId);
        deliveryService.sendProduct(orderId);
        return "redirect:/admin/orders";
    }
}
