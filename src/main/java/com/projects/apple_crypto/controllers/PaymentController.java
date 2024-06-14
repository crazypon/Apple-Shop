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
import com.projects.apple_crypto.entities.Customer;
import com.projects.apple_crypto.entities.CustomerRepository;
import com.projects.apple_crypto.entities.OrderRepository;



@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderRepository orderRepo;

    @GetMapping
    public String paymentPage() {
        return "payment";
    }
    
    @PostMapping
    public String paymentSuccess(@ModelAttribute Customer customer, @RequestParam("productId") Long productId, Model model) {

        System.out.println("Inside Post Mapping Controller in Success");
        // saving customer
        customerRepo.save(customer);

        // saving order
        System.out.println("Here is product Id" + productId);
        AppleCryptoOrder order = new AppleCryptoOrder(customer.getId(), productId);
        orderRepo.save(order);

        return "success";
    }
}
