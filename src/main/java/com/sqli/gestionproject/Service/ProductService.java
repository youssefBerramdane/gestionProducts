package com.sqli.gestionproject.Service;

import com.sqli.gestionproject.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto update(String code,ProductDto productDto);
    List<ProductDto>findAll();
    ProductDto findByCode(String code);

    void deleteById(String code);
}
