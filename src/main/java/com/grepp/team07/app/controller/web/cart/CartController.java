package com.grepp.team07.app.controller.web.cart;

import com.grepp.team07.app.model.auth.domain.Principal;
import com.grepp.team07.app.model.cart.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addCart(@RequestParam("productId") int productId,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          HttpSession session) {
        String userId = getLoginUserId();
        cartService.addProduct(productId, 1, session, userId);
        return "redirect:/product?page=" + page;
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        String userId = getLoginUserId();
        cartService.clearCart(session, userId);
        return "redirect:/product";
    }

    private String getLoginUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }
        Principal principal = (Principal) auth.getPrincipal();
        return principal.getUsername();
    }
}
