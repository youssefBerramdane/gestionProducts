package com.sqli.gestionproject.ServiceImplementation;

import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Exception.ProductNotFoundException;
import com.sqli.gestionproject.Mapper.MappersDto;
import com.sqli.gestionproject.Repository.CategoryRepository;
import com.sqli.gestionproject.Repository.ProductRepository;
import com.sqli.gestionproject.Service.ProductService;
import com.sqli.gestionproject.Service.ServiceUtills.ServiceChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private MappersDto mappers;
    private CategoryRepository categoryRepository;
    private ServiceChecker serviceChecker;

    @Override
    public ProductDto save(ProductDto productDto) {
        String categoryCode = productDto.getCategory().getCode();
        String productCode = productDto.getCode();
        double price = productDto.getPrice();
        if (serviceChecker.checkProductConditions(productCode, categoryCode, price)) {
            Product product = productRepository.save(mappers.dtoToProduct(productDto));
            return mappers.productTodto(product);
        }
        return null;

    }

    @Override
    public ProductDto update(String code, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(code);
        if (serviceChecker.productExist(product)) {
            if (serviceChecker.checkProductConditions("", productDto.getCategory().getCode(), productDto.getPrice())) {
                return mappers.productTodto(productRepository.save(mappers.dtoToProduct(productDto)));
            }

        }
        throw new ProductNotFoundException(String.format("Product with id %s not found", code));

    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(mappers::productTodto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findByCode(String code) {
        Optional<Product> product = productRepository.findById(code);
        if (serviceChecker.productExist(product)) {
            return mappers.productTodto(product.get());
        }
        throw new ProductNotFoundException(String.format("Product With id %s not found", code));
    }

    @Override
    public void deleteById(String code) {
        productRepository.deleteById(code);
    }

   /* //-----------------------------------------------//
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

    public boolean productExist(Optional<Product> product) {
        return product.isPresent();
    }

    public boolean checkProductConditions(String productCode,String categoryCode,double price){
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
    }*/
}



/*if (categoryExist(categoryCode)) {
            if (codeNotExist(productCode)) {
                if (PriceIfPositif(price)) {
                    Product product = mappers.dtoToProduct(productDto);
                    product.setCategory(findCategoryById(categoryCode));
                    productRepository.save(mappers.dtoToProduct(productDto));
                    return mappers.productTodto(product);
                }
                throw new PriceNotPositifException("Price must be greater than 0");
            }
            throw new DuplicateProductCodeException(String.format("The code %s already exist", productCode));
        }
        throw new CategoryNotFoundException(String.format("Category with code %s not found", categoryCode));*/
