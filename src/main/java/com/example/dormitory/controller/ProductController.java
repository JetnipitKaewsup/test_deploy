package com.example.dormitory.controller;

import com.example.dormitory.model.Product;
import com.example.dormitory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(
            @Valid @ModelAttribute Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "product-form";
        }

        productService.save(product);

        return "redirect:/";
    }
}
