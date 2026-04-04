package com.springbootproject.product.mapper;

import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;

public class ProductMapper {

    /* Mapper-> (Entity To DTO) OR (DTO To Entity)*/

    // Entity to DTO
    public static ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }

    // DTO To Entity
     public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
     }

}
