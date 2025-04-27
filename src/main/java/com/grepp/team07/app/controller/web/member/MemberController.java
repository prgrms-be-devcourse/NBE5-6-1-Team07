package com.grepp.team07.app.controller.web.member;

import com.grepp.team07.app.controller.web.member.form.MemberEditForm;
import com.grepp.team07.app.controller.web.member.form.SigninForm;
import com.grepp.team07.app.controller.web.member.form.SignupForm;
import com.grepp.team07.app.model.auth.code.Role;
import com.grepp.team07.app.model.cart.CartService;
import com.grepp.team07.app.model.cart.dto.CartProductDto;
import com.grepp.team07.app.model.member.dto.Member;
import com.grepp.team07.app.model.member.service.MemberService;
import com.grepp.team07.app.model.product.ProductService;
import com.grepp.team07.app.model.product.dto.ProductDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/signin")
    public String signin(SigninForm form){
        return "member/signin";
    }

    @GetMapping("/signup")
    public String signup(SignupForm form) {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(
        @Valid SignupForm form,
        BindingResult bindingResult,
        Model model) {

        if (bindingResult.hasErrors()) {
            return "member/signup";
        }

        memberService.signup(form.toDto(), Role.ROLE_USER);
        return "redirect:/";
    }


    @GetMapping("mypage")
    public String mypage(Authentication authentication, HttpSession session, Model model){
        String userId = authentication.getName();
        Member info = memberService.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다: " + userId));

        model.addAttribute("info", info);

        List<CartProductDto> cartItems = cartService.getCartItems(session, userId);
        model.addAttribute("cartItems", cartItems);

        Map<Integer, String> productNames = new HashMap<>();
        Map<Integer, Integer> productPrices = new HashMap<>();

        for (CartProductDto item : cartItems) {
            ProductDto dto = productService.findById(item.getProductId());
            productNames.put(item.getProductId(), dto.getName());
            productPrices.put(item.getProductId(), Integer.parseInt(dto.getPrice()));
        }
        model.addAttribute("productNames", productNames);
        model.addAttribute("productPrices", productPrices);

        model.addAttribute("loginEmail", info.getEmail());
        model.addAttribute("loginAddress", info.getAddress());
        model.addAttribute("loginPostCode", info.getPostCode());

        return "member/mypage";
    }

    @GetMapping("edit")
    public String editMypage(MemberEditForm form, Authentication authentication, Model model){
        String userId = authentication.getName();
        Optional<Member> info =  memberService.findByUserId(userId);
        form.setUserId(info.get().getUserId());
        form.setEmail(info.get().getEmail());
        form.setPassword(null);
        form.setAddress(info.get().getAddress());
        form.setPostCode(info.get().getPostCode());

        model.addAttribute("customerEditForm", form);
        model.addAttribute("info", info);
        return "/member/mypageEdit";
    }

    @PostMapping("edit")
    public String editMypage(@Valid MemberEditForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "redirect:/member/edit";
        }

        if (form.getPassword() != null && !form.getPassword().isEmpty()) {
            form.setPassword(passwordEncoder.encode(form.getPassword()));
        } else {
            form.setPassword(passwordEncoder.encode(form.getCheckpassword()));
        }

        memberService.edit(form.toDto());
        return "redirect:/member/mypage";
    }

    @PostMapping("check-password")
    @ResponseBody
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        String password = payload.get("password");
        boolean valid = memberService.checkPassword(userId, password);
        return Collections.singletonMap("valid", valid);
    }
}