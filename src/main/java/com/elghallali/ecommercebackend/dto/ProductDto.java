package com.elghallali.ecommercebackend.dto;

import com.elghallali.ecommercebackend.entity.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ProductDto {
    @NotBlank
    private String name;
    @Min(0)
    private double price;
    private Date addedOn;
    @NotBlank
    private Category category;
    private String description;

    public ProductDto() {
    }

    public ProductDto(@NotBlank String name, @Min(0) double price, Date addedOn, @NotBlank Category category, String description) {
        this.name = name;
        this.price = price;
        this.addedOn = addedOn;
        this.category = category;
        this.description = description;
    }
}
