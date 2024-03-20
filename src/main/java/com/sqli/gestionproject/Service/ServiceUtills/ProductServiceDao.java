package com.sqli.gestionproject.Service.ServiceUtills;

import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceDao {

    private ProductRepository productRepository;

    public List<Product>findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductByCode(String code){
        return productRepository.findById(code).get();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(String code){
        productRepository.deleteById(code);
    }

}
