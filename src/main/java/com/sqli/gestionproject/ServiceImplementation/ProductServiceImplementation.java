package com.sqli.gestionproject.ServiceImplementation;

import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Exception.ProductNotFoundException;
import com.sqli.gestionproject.Mapper.MappersDto;
import com.sqli.gestionproject.Service.ProductService;
import com.sqli.gestionproject.Service.ServiceUtills.ProductServiceDao;
import com.sqli.gestionproject.Service.ServiceUtills.ServiceChecker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImplementation implements ProductService {

    //private ProductRepository productRepository;
    private MappersDto mappers;
    private ProductServiceDao productOperations;
    private ServiceChecker serviceChecker;

    @Override
    public String save(ProductDto productDto) {
        return saveValidateProduct(productDto);
    }

    @Override
    public String update(String code, ProductDto productDto) {
        return updateValidateProduct(code, productDto);
    }

    @Override
    public List<ProductDto> findAll() {
        return productOperations.findAllProducts().stream().map(mappers::productTodto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findByCode(String code) {
        if (serviceChecker.isProductPresent(code)) {
            return mappers.productTodto(productOperations.findProductByCode(code));
        }
        throw new ProductNotFoundException(String.format("Product With id %s not found", code));
    }

    @Override
    public void deleteProductByIdIfExist(String code) {
        if (serviceChecker.isProductPresent(code)) {
            productOperations.deleteProductById(code);
        } else {
            throw new ProductNotFoundException(String.format("Product with id %s not found", code));
        }
    }


    public String saveValidateProduct(ProductDto productDto) {
        String categoryCode = productDto.getCategory().getCode();
        String productCode = productDto.getCode();
        double price = productDto.getPrice();
        if (serviceChecker.validateProduct(productCode, categoryCode, price)) {
            Product product = productOperations.saveProduct(mappers.dtoToProduct(productDto));
            return product.getCode();
        }
        throw new RuntimeException("Invalid product details provided.");
    }

    public String updateValidateProduct(String code,ProductDto productDto){
        if(serviceChecker.isProductPresent(code)){
            if (serviceChecker.validateProduct("anyCode", productDto.getCategory().getCode(), productDto.getPrice())) {
                Product product=productOperations.findProductByCode(code);
                return updateProductFields(product,productDto);
            }

        }
        throw new ProductNotFoundException(String.format("Product with id %s not found",code));

    }

    public String updateProductFields(Product product,ProductDto productDto){
        product.setPrice(productDto.getPrice());
        product.setCategory(mappers.dtoToCategory(productDto.getCategory()));
        return productOperations.saveProduct(product).getCode();
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
