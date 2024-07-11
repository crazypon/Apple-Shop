package com.projects.apple_crypto.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.projects.apple_crypto.services.RequestLoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    RequestLoggingInterceptor requestLoggingInterceptor;
    
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(requestLoggingInterceptor);
    }
}
