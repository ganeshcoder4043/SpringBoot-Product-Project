package com.springbootproject.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Product",
        description = "It holds product information."
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    /* DTO->(Data Transfer object) Es Class Mein Srif Whi Data Likhenge Jo Hamen Jaurart hai */

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long  CategoryId; // Category id esliye liyen pta chl sken ki product kis category ki hai
}
