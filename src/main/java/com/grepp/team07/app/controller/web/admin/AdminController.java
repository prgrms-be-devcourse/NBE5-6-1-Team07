package com.grepp.team07.app.controller.web.admin;

import com.grepp.team07.app.controller.web.admin.form.ProductInsertForm;
import com.grepp.team07.app.model.admin.AdminService;
import com.grepp.team07.app.model.product.dto.ProductDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("product")
    public String list(
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String action,
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        if (id != null && action != null) {
            if (action.equals("increment")) {
                adminService.incrementCount(id);
            } else if (action.equals("decrement")) {
                adminService.decrementCount(id);
            } else if (action.equals("delete")) {
                adminService.delete(id);
                return "redirect:/admin/product";
            }
        }

        List<ProductDto> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = adminService.searchByKeyword(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            products = adminService.findAll();
        }

        model.addAttribute("products", products);

        if (id != null) {
            ProductDto product = adminService.findById(id);
            model.addAttribute("productInsertForm", product);
        } else {
            model.addAttribute("productInsertForm", new ProductInsertForm());
        }

        return "admin/product";
    }


    @PostMapping("product")
    public String insertOrUpdate(
        @Valid ProductInsertForm form,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/product";
        }

        if (form.getProductId() != null && form.getProductId() > 0) {
            adminService.updateProduct(form.getThumbnail(), form.fromForm());
        } else {
            adminService.insertProduct(form.getThumbnail(), form.toDto());
        }

        return "redirect:/admin/product";
    }

}
