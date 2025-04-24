package com.grepp.team07.app.controller.web.product;

import com.grepp.team07.app.controller.web.product.form.ProductInsertForm;
import com.grepp.team07.app.model.product.ProductService;
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

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("product")
    public String list(Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productInsertForm", new ProductDto());
        return "admin/product";
    }

    @PostMapping("product")
    public String insert(
        @Valid
        ProductInsertForm form,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/product";
        }
        productService.insertProduct(form.toDto());
        return "redirect:/";
    }

}
