package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.Customer;
import com.projects.apple_crypto.entities.ProductRepo;


@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductRepo productRepo;


    @PostMapping
    public String redirectToPayment(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "payment";
    }

    // This is not for get request, this is for linking to another page 
    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products";
    }
}

