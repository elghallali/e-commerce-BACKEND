package com.elghallali.ecommercebackend.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
    @NotBlank
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(@NotBlank String name) {
        this.name = name;
    }
}
