package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.apple_crypto.entities.AppleCryptoOrder;
import com.projects.apple_crypto.entities.User;
import com.projects.apple_crypto.entities.UserRepository;
import com.projects.apple_crypto.entities.OrderRepository;



@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderRepository orderRepo;

    @GetMapping
    public String paymentPage() {
        return "payment";
    }
    
    @PostMapping
    public String paymentSuccess(@ModelAttribute User user, @RequestParam("productId") Long productId, Model model) {
        // saving customer
        userRepo.save(user);

        // saving order
        AppleCryptoOrder order = new AppleCryptoOrder(user.getId(), productId);
        orderRepo.save(order);

        return "success";
    }
}
