package com.springbootproject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    /* DTO->(Data Transfer object) Es Class Mein Srif Whi Data Likhenge Jo Hamen Jaurart hai */

    private Long id;
    private String name;
    private List<ProductDTO> products ;
}
