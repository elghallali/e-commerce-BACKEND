package com.elghallali.ecommercebackend.service;

import com.elghallali.ecommercebackend.entity.Product;
import com.elghallali.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Optional<Product> getByName(String name){
        return productRepository.findByName(name);
    }
    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }
}
