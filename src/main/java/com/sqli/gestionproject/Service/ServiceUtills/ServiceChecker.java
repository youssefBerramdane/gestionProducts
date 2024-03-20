package com.sqli.gestionproject.Service.ServiceUtills;

import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Exception.CategoryNotFoundException;
import com.sqli.gestionproject.Exception.CodeNullException;
import com.sqli.gestionproject.Exception.DuplicateProductCodeException;
import com.sqli.gestionproject.Exception.PriceNotPositifException;
import com.sqli.gestionproject.Mapper.MappersDto;
import com.sqli.gestionproject.Repository.CategoryRepository;
import com.sqli.gestionproject.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ServiceChecker {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private MappersDto mappers;

    //check If Product's category exist in DB
    public boolean isCategoryPresent(String categoryCode) {
        return categoryRepository.findById(categoryCode).isPresent();
    }

    // Valid category
    public boolean validateCategoryExistence(String categoryCode) {
        return !Optional.of(categoryCode).filter(this::isCategoryPresent).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with code %s not found", categoryCode))).isEmpty();
    }

    //Check product's code not exist in DB
    public boolean codeNotExist(String code) {
        return productRepository.findById(code).isEmpty();
    }

    //Check Product's code not null
    public boolean codeNotNull(String code) {
        return !code.isEmpty();
    }

    public boolean codeNotExistAndNotNull(String code) {
        boolean codeNotNull = !Optional.ofNullable(code).filter(this::codeNotNull).orElseThrow(() -> new CodeNullException("Code must not be null")).isEmpty();
        boolean codeNotExist = !Optional.ofNullable(code).filter(this::codeNotExist).orElseThrow(() -> new DuplicateProductCodeException(String.format("The code %s already exist", code))).isEmpty();
        return codeNotExist && codeNotNull;
    }

    public boolean validateProductCode(String code) {
        return codeNotExistAndNotNull(code);
    }

    //Check if product's price greater than 0
    public boolean isPricePositive(double price) {
        return price > 0;
    }

    public boolean validatePrice(double price) {
        return !Optional.ofNullable(price).filter(this::isPricePositive).orElseThrow(() -> new PriceNotPositifException("Price must be greater than 0")).isNaN();
        //return true;
    }

    public boolean isProductPresent(String code) {
        return productRepository.findById(code).isPresent();
    }

    public boolean validateProduct(String productCode, String categoryCode, double price) {

        return (validateProductCode(productCode) && validateCategoryExistence(categoryCode) && validatePrice(price));
    }


}
