package com.elghallali.ecommercebackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

    @GetMapping
    public String greeting(){
        return "Hello world";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/do")
    public String toDo(){
        return "You are the admin";
    }
}
