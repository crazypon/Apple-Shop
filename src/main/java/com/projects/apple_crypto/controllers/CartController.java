package com.projects.apple_crypto.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.apple_crypto.entities.Product;
import com.projects.apple_crypto.entities.ProductRepo;
import com.projects.apple_crypto.entities.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping("/cart")
    public String cartPage(HttpSession session, Model model) {
        List<String> productIds = (List<String>) session.getAttribute("items");
        List<Product> products = new ArrayList<>();

        for (String productId : productIds) {
            // turning String into Long
            Long currentId = Long.parseLong(productId);

            Optional<Product> currentProduct = productRepo.findById(currentId);
            // getting Product from Optional
            products.add(currentProduct.get());
        }
    
        if(productIds != null) {
            model.addAttribute("products", products);
        }

        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(HttpSession session, @RequestParam("productId") Long productId) {
        List<String> cart = (List<String>) session.getAttribute("items");
        if(cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(Long.toString(productId));
        session.setAttribute("items", cart);
        return "redirect:/cart";
    }
}
