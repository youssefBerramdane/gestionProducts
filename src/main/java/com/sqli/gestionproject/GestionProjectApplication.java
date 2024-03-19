package com.sqli.gestionproject;

import com.sqli.gestionproject.Entity.Category;
import com.sqli.gestionproject.Repository.CategoryRepository;
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
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository){
        return args -> {
            categoryRepository.save(Category.builder().code("MA060504").libelle("Clothes").build());
            categoryRepository.save(Category.builder().code("MA070712").libelle("Sport").build());
            categoryRepository.save(Category.builder().code("MA668130").libelle("Technology").build());
        };
    }
}
