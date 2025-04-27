package com.grepp.team07.app.controller.web.product;

import com.grepp.team07.app.model.auth.domain.Principal;
import com.grepp.team07.app.model.cart.CartService;
import com.grepp.team07.app.model.cart.dto.CartProductDto;
import com.grepp.team07.app.model.member.dto.Member;
import com.grepp.team07.app.model.member.repository.MemberRepository;
import com.grepp.team07.app.model.product.ProductService;
import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.infra.exceptions.CommonException;
import com.grepp.team07.infra.payload.PageParam;
import com.grepp.team07.infra.response.PageResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;
    private final MemberRepository memberRepository;

    @GetMapping("")
    public String productList(@Valid PageParam param,
                              BindingResult bindingResult,
                              @RequestParam(value = "item", required = false) String item,
                              HttpSession session,
                              Model model) {

        List<ProductDto> products = (item == null || item.isBlank())
            ? productService.selectAll()
            : productService.searchProducts(item);
        model.addAttribute("products", products);

        // 장바구니 조회
        String userId = getLoginUserId();
        List<CartProductDto> cartItems = cartService.getCartItems(session, getLoginUserId());
        model.addAttribute("cartItems", cartItems);

        Map<Integer, String> productNames = new HashMap<>();
        for (CartProductDto i : cartItems) {
            ProductDto dto = productService.findById(i.getProductId());
            productNames.put(i.getProductId(), dto.getName());
        }
        model.addAttribute("productNames", productNames);

        Page<ProductDto> page;
        if (item == null || item.isBlank()) {
            Pageable pageable = PageRequest.of(param.getPage() - 1, param.getSize());
            page = productService.findPaged(pageable);
        } else {
            page = new PageImpl<>(products);
        }

        if (param.getPage() != 1 && page.getContent().isEmpty()){
            throw new CommonException();
        }

        PageResponse<ProductDto> response = new PageResponse<>("/product", page, 3);
        model.addAttribute("page", response);

        if (userId != null) {
            Member member = memberRepository.selectByUserId(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다: " + userId));

            model.addAttribute("loginEmail", member.getEmail());
            model.addAttribute("loginAddress", member.getAddress());
            model.addAttribute("loginPostCode", member.getPostCode());
        }

        return "product/product-list";
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
