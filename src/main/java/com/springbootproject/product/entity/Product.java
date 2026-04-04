package com.springbootproject.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne   // ↓  yeh product class mein kis product ki kis category ka hai then we use @JoinColumn
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
