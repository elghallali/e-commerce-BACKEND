package com.elghallali.ecommercebackend.controller;

import com.elghallali.ecommercebackend.dto.Message;
import com.elghallali.ecommercebackend.dto.ProductDto;
import com.elghallali.ecommercebackend.entity.Category;
import com.elghallali.ecommercebackend.entity.Product;
import com.elghallali.ecommercebackend.service.CategoryService;
import com.elghallali.ecommercebackend.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductsController {

   @Autowired
    private ProductService productService;

   @Autowired
    private CategoryService categoryService;

   @GetMapping("/list")
    public ResponseEntity<List<Product>> listProducts(){
       List<Product> list = productService.getProducts();
       List<Product> productList = new ArrayList<>();
       for (Product product:list) {
           if (product.getStatus() != "DELETED")
               productList.add(product);
       }
       return new ResponseEntity(productList, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<List<Product>> listProductsAdmin(){
        List<Product> list = productService.getProducts();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> detailProduct(@PathVariable("id") Long id){
       if (!productService.existsById(id))
           return new ResponseEntity(new Message("Product not exist"),HttpStatus.NOT_FOUND);
       Product product = productService.getById(id).get();
       return new ResponseEntity(product,HttpStatus.OK);
    }

    @GetMapping("/detail/{name}")
    public ResponseEntity<Product> detailProduct(@PathVariable("name") String name){
        if (!productService.existsByName(name))
            return new ResponseEntity(new Message("Product not exist"),HttpStatus.NOT_FOUND);
        Product product = productService.getByName(name).get();
        return new ResponseEntity(product,HttpStatus.OK);
    }

    @GetMapping("/{category}/{id}")
    public ResponseEntity<List<Product>> listProductCategory(@PathVariable("id") Long id, @PathVariable("category") String categoryName){
       if (!categoryService.existById(id))
           return new ResponseEntity(new Message("Category not exist"), HttpStatus.BAD_REQUEST);
       if (categoryService.existByName(categoryName) && categoryService.getByName(categoryName).get().getId() != id)
           return new ResponseEntity(new Message("Category not exist"), HttpStatus.BAD_REQUEST);
        Category category = categoryService.getByName(categoryName).get();
       List<Product> list = productService.getByCategory(category);
       return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){

       if (StringUtils.isBlank(productDto.getName()))
           return new ResponseEntity(new Message("The product name is required"),HttpStatus.BAD_REQUEST);
       if (productDto.getPrice() < 0)
           return new ResponseEntity(new Message("The price must be greater then 0"),HttpStatus.BAD_REQUEST);
        if (productDto.getCategory() == null && !categoryService.existById(productDto.getCategory().getId()))
            return new ResponseEntity(new Message("The category not exist and not should be empty"),HttpStatus.BAD_REQUEST);
       Product product = new Product(
               productDto.getName(),
               productDto.getPrice(),
               productDto.getStock(),
               productDto.getCategory(),
               productDto.getDescription()
       );
       product.setStatus("CREATED");
       product.setCreateAt(new Date());
       productService.save(product);

       return new ResponseEntity(new Message("That product is created"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto){
        if (!productService.existsById(id))
            return new ResponseEntity(new Message("The product not exist"),HttpStatus.BAD_REQUEST);
        if (productService.existsByName(productDto.getName()) && productService.getByName(productDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("That name is already exist"),HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("The product name is required"),HttpStatus.BAD_REQUEST);
        if (productDto.getPrice() < 0)
            return new ResponseEntity(new Message("The price must be greater then 0"),HttpStatus.BAD_REQUEST);
       Product product = productService.getById(id).get();
       product.setName(productDto.getName());
       product.setPrice(productDto.getPrice());
       product.setStock(productDto.getStock());
       product.setDescription(productDto.getDescription());
       product.setStatus("UPDATED");
       product.setLastUpdateAt(new Date());
       productService.save(product);
       return new ResponseEntity(new Message("That product is updated"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        if (!productService.existsById(id))
            return new ResponseEntity(new Message("The product not exist"),HttpStatus.BAD_REQUEST);
        Product product = productService.getById(id).get();
        product.setStatus("DELETED");
        product.setLastUpdateAt(new Date());
        productService.save(product);
        return new ResponseEntity(new Message("That product is deleted"), HttpStatus.OK);
    }




}
