package com.projects.apple_crypto.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projects.apple_crypto.entities.Product;
import com.projects.apple_crypto.entities.ProductRepo;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/addProduct")
@RequiredArgsConstructor
public class NewProductController {
    
    private final RabbitTemplate rabbitTemplate;
    private final ProductRepo productRepo;

    @GetMapping
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping
    public String saveNewProduct(@RequestParam("image") MultipartFile file, @ModelAttribute("product") Product product) throws IOException{
        // sending image and product information 
        // byte[] imageBytes = file.getBytes();

        // Map<String, Object> message = new HashMap<>();
        // message.put("image", imageBytes);
        // message.put("product", product);
        String uploadDir = "uploads";
        Path directory = Paths.get(uploadDir);
        
        // saving to local directory
        try {
            Files.copy(file.getInputStream(), directory.resolve(file.getOriginalFilename()));
        }catch (Exception e) {

            if (e instanceof FileAlreadyExistsException) {
            throw new RuntimeException("A file of that name already exists.");

      }

        throw new RuntimeException(e.getMessage());
    }

        
        product.setImagePath(file.getOriginalFilename());

        productRepo.save(product);

        return "redirect:/admin";



        // rabbitTemplate.convertAndSend("testExchange", "first.key", imageBytes);   
    }
}
