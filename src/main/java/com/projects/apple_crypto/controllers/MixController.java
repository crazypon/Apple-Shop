package com.projects.apple_crypto.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MixController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry view) {
        view.addViewController("/").setViewName("home");
    }
}
