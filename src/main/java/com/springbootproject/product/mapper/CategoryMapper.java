package com.springbootproject.product.mapper;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.entity.Category;

public class CategoryMapper {

    /* Mapper-> (Entity To DTO) OR (DTO To Entity)*/

    // Entity to DTO
    public static CategoryDTO toCategoryDTO(Category category){
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(category.getProducts()
                .stream()
                .map(ProductMapper::toProductDTO)
                .toList());
        return categoryDTO;
    }

    // DTO to Entity
    public static Category toCategoryEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
