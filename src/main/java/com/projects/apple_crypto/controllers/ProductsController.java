package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.apple_crypto.entities.User;
import com.projects.apple_crypto.entities.ProductRepo;


@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductRepo productRepo;


    @PostMapping
    public String redirectToPayment(@RequestParam("productId") long productId, Model model) {
        User user = new User();
        model.addAttribute("customer", user);
        model.addAttribute("productId", productId);
        System.out.println("Before putting id into thymleaf: " + productId);
        return "payment";
    }

    
    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products";
    }
}

