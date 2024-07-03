package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.apple_crypto.entities.User;
import com.projects.apple_crypto.entities.UserRepository;


@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserRepository userRepo;

    @GetMapping
    public String profilePage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username).orElse(null);
        model.addAttribute("user", currentUser);
        return "profile";
    }
}
