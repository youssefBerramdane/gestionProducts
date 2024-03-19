package com.sqli.gestionproject;

import com.sqli.gestionproject.Entity.Category;
import com.sqli.gestionproject.Entity.Product;
import com.sqli.gestionproject.Repository.CategoryRepository;
import com.sqli.gestionproject.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository){
        return args -> {
            categoryRepository.save(Category.builder().code("MA060504").name("Clothes").build());
            categoryRepository.save(Category.builder().code("MA070712").name("Sport").build());
            categoryRepository.save(Category.builder().code("MA668130").name("Technology").build());
            productRepository.save(Product.builder().code("PC1564").price(300).category(categoryRepository.getReferenceById("MA668130")).build());
            productRepository.save(Product.builder().code("SHOE1658").price(20).category(categoryRepository.getReferenceById("MA070712")).build());
            productRepository.save(Product.builder().code("PR15564").price(100).category(categoryRepository.getReferenceById("MA668130")).build());
            productRepository.save(Product.builder().code("GL5566").price(200).category(categoryRepository.getReferenceById("MA070712")).build());

        };
    }
}
