package com.sqli.gestionproject.Service;

import com.sqli.gestionproject.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    String save(ProductDto productDto);

    String update(String code, ProductDto productDto);

    List<ProductDto> findAll();

    ProductDto findByCode(String code);

    void deleteProductByIdIfExist(String code);
}
