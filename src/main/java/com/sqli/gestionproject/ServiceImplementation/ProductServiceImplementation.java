package com.sqli.gestionproject.ServiceImplementation;

import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    @Override
    public ProductDto save(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto update(String code,ProductDto productDto) {
        return null;
    }

    @Override
    public List<ProductDto> findAll() {
        return null;
    }

    @Override
    public ProductDto findByCode(String code) {
        return null;
    }

    @Override
    public void deleteById(String code) {

    }
}
