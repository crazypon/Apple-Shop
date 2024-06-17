package com.projects.apple_crypto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContoller {
    @GetMapping("/admin")
    public String adminPage() {
        return "admin_page";
    }
}
