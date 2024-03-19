package com.sqli.gestionproject.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Product {
    @Id
    private String code;
    private double price;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
