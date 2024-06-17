package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.Customer;
import com.projects.apple_crypto.security.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;
    
    @GetMapping
    public String registrationPage(Model model) {
        System.out.println("In Registration Controller");
        model.addAttribute("userForm", new Customer());
        return "registration";
    }

    @PostMapping
    public String successRegistryPage(@ModelAttribute("userForm") Customer customer) {

        if (!customer.getPassword().equals(customer.getPasswordConfirm())) {
            System.out.println("EEERRRRRORRRRR passwords are not the sameeee!");
            return "registration";
        }

        if (!userService.saveUser(customer)) {
            System.out.println("EEERRRRORRRR this username is busy!!!");
            return "registration";
        }
        return "redirect:/";
    }
    
}
