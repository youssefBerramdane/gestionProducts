package com.sqli.gestionproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "categories")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Category {
    @Id
    private String code;
    private String name;
}
