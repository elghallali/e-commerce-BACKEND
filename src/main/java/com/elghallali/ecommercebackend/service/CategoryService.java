package com.elghallali.ecommercebackend.service;

import com.elghallali.ecommercebackend.entity.Category;
import com.elghallali.ecommercebackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getOne(Long id){
        return categoryRepository.findById(id);
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public Optional<Category> getByName(String name){
        return categoryRepository.findByName(name);
    }

    public boolean existByName(String name){
        return categoryRepository.existsByName(name);
    }
    public boolean existById(Long id){
        return categoryRepository.existsById(id);
    }
}
