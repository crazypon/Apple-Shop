package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.User;
import com.projects.apple_crypto.marker_interfaces.UserInformation;
import com.projects.apple_crypto.security.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;
    
    @GetMapping
    public String registrationPage(Model model) {
        System.out.println("In Registration Controller");
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping
    public String successRegistryPage(
            @Validated(UserInformation.class) @ModelAttribute("userForm") User user,
            BindingResult binding,
            Model model
        ) {
        // TODO Add password confirmation validation
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords are not the same");
            return "registration";
        }

        // TODO Add unique username exception
        if (!userService.saveUser(user)) {
            model.addAttribute("uniqueUsernameError", "This username is busy");
            return "registration";
        }

        if(binding.hasErrors()) {
            return "registration";
        }
        return "redirect:/login";
    }
    
}
