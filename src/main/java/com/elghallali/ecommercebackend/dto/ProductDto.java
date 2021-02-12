package com.elghallali.ecommercebackend.dto;

import com.elghallali.ecommercebackend.entity.Category;

import javax.validation.constraints.*;


public class ProductDto {
    @NotBlank
    private String name;
    @Min(0)
    private double price;
    private int stock;
    @NotNull(message = "the category no should be empty")
    private Category category;
    private String description;

    public ProductDto() {
    }

    public ProductDto(@NotBlank String name,
                      @Min(0) double price,
                      int stock,
                      @NotNull(message = "the category no should be empty") Category category,
                      String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
