package com.app.threetier.controller;

import com.app.threetier.domain.vo.ProductVO;
import com.app.threetier.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products/*")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @GetMapping("list")
    public String selectAll(Model model) {
        model.addAttribute("products", productService.selectAll());
    return "products/result";
    }
}
