package com.elghallali.ecommercebackend.controller;

import com.elghallali.ecommercebackend.dto.CategoryDto;
import com.elghallali.ecommercebackend.dto.Message;
import com.elghallali.ecommercebackend.entity.Category;
import com.elghallali.ecommercebackend.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> list = categoryService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        if (!categoryService.existById(id))
            return new ResponseEntity(new Message("Category not exist"),HttpStatus.BAD_REQUEST);
        Category category = categoryService.getOne(id).get();
        return new ResponseEntity(category,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto){
        if (StringUtils.isBlank(categoryDto.getName()))
            return new ResponseEntity(new Message("The category name is required"),HttpStatus.BAD_REQUEST);
        if (categoryService.existByName(categoryDto.getName()))
            return new ResponseEntity(new Message("The category name is already exist"),HttpStatus.BAD_REQUEST);
        Category category = new Category(categoryDto.getName());
        category.setStatus("CREATED");
        categoryService.save(category);
        return new ResponseEntity(new Message("a new category is created"),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto){
        if(!categoryService.existById(id))
            return new ResponseEntity(new Message("Category not exist"),HttpStatus.BAD_REQUEST);
        if (categoryService.existByName(categoryDto.getName()) && categoryService.getByName(categoryDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("That category name is already exist"),HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(categoryDto.getName()))
            return new ResponseEntity(new Message("The category name is required"),HttpStatus.BAD_REQUEST);
        Category category = categoryService.getOne(id).get();
        category.setName(categoryDto.getName());
        category.setStatus("UPDATED");
        categoryService.save(category);
        return new ResponseEntity(new Message("That category is updated"),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!categoryService.existById(id))
            return new ResponseEntity(new Message("The category is exist"),HttpStatus.BAD_REQUEST);
        Category category = categoryService.getOne(id).get();
        category.setStatus("DELETED");
        categoryService.save(category);
        return new ResponseEntity(new Message("That category is deleted"),HttpStatus.OK);
    }


}
