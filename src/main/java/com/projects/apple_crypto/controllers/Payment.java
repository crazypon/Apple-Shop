package com.projects.apple_crypto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.Customer;



@Controller
@RequestMapping("/payment")
public class Payment {
    
    @PostMapping
    public String paymentSuccess(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("payment", customer);
        return "success";
    }
}
