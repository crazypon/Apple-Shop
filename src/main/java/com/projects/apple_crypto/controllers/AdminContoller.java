package com.projects.apple_crypto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projects.apple_crypto.services.RequestLoggingInterceptor;


@Controller
public class AdminContoller {

    @Autowired
    RequestLoggingInterceptor requestLoggingInterceptor;

    @GetMapping("/admin")
    public String websiteData(Model model) {
        model.addAttribute("requests", requestLoggingInterceptor.getRequestInfoList());
        return "admin_page";
    }
}
