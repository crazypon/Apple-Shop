package com.projects.apple_crypto.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.ProductRepo;


@Controller
@RequestMapping("/")
public class ProductsController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping
    public String helloWorld(Model model) {
        model.addAttribute("something", "This is coming from Controller");
        return "home";
    }


    // This is not for get request, this is for linking to another page 
    @RequestMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products";
    }
}
