package com.grepp.team07.app.controller.web.product;

import com.grepp.team07.app.model.product.ProductService;
import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.infra.exceptions.CommonException;
import com.grepp.team07.infra.payload.PageParam;
import com.grepp.team07.infra.response.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public String productList(@Valid PageParam param,
                              BindingResult bindingResult,
                              @RequestParam(value = "item", required = false) String item,
                              Model model) {

        List<ProductDto> products = (item == null || item.isBlank())
            ? productService.selectAll()
            : productService.searchProducts(item);
        model.addAttribute("products", products);

        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getSize());
        Page<ProductDto> page = productService.findPaged(pageable);

        if(param.getPage() != 1 && page.getContent().isEmpty()){
            throw new CommonException();
        }

        PageResponse<ProductDto> response = new PageResponse<>("/product", page, 3);
        model.addAttribute("page", response);

        return "product";
    }
}
