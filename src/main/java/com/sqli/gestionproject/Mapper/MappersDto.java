package com.sqli.gestionproject.Mapper;

import com.sqli.gestionproject.Dto.CategoryDto;
import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Entity.Category;
import com.sqli.gestionproject.Entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MappersDto {

    public Product dtoToProduct(ProductDto productDto){
        Product product= Product.builder().build();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

    public ProductDto productTodto(Product product){
        ProductDto productDto= ProductDto.builder().build();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        Category category= Category.builder().build();
        BeanUtils.copyProperties(categoryDto,category);
        return category;
    }

    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto= CategoryDto.builder().build();
        BeanUtils.copyProperties(category,categoryDto);
        return categoryDto;
    }
}
