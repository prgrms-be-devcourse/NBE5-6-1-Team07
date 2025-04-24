package com.grepp.team07.app.controller.web.member;

import com.grepp.team07.app.controller.web.member.form.SigninForm;
import com.grepp.team07.app.controller.web.member.form.SignupForm;
import com.grepp.team07.app.model.auth.code.Role;
import com.grepp.team07.app.model.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

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
}