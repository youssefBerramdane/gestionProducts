package com.sqli.gestionproject.Dto;

import com.sqli.gestionproject.Entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ProductDto {

    private String code;
    private double price;
    private CategoryDto category;
}
