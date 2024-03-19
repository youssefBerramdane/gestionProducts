package com.sqli.gestionproject.Service.ServiceUtills;

import com.sqli.gestionproject.Entity.Category;
import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Exception.CategoryNotFoundException;
import com.sqli.gestionproject.Exception.DuplicateProductCodeException;
import com.sqli.gestionproject.Exception.PriceNotPositifException;
import com.sqli.gestionproject.Repository.CategoryRepository;
import com.sqli.gestionproject.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ServiceChecker {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public Category findCategoryById(String categoryCode) {
        return categoryRepository.findById(categoryCode).get();
    }

    public boolean categoryExist(String categoryCode) {
        return categoryRepository.findById(categoryCode).isPresent();
    }

    public boolean codeNotExist(String code) {
        return productRepository.findById(code).isEmpty();

    }

    public boolean PriceIfPositif(double price) {
        return price > 0;
    }

    public static boolean productExist(Optional<Product> product) {
        return product.isPresent();
    }

    public boolean checkProductConditions(String productCode, String categoryCode, double price) {
        if (categoryExist(categoryCode)) {
            if (codeNotExist(productCode)) {
                if (PriceIfPositif(price)) {
                    return true;
                }
                throw new PriceNotPositifException("Price must be greater than 0");
            }
            throw new DuplicateProductCodeException(String.format("The code %s already exist", productCode));
        }
        throw new CategoryNotFoundException(String.format("Category with code %s not found", categoryCode));
    }
}
