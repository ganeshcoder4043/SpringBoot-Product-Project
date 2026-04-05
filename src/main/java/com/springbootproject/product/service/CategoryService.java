package com.springbootproject.product.service;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.exception.CategoryAlreadyExistsException;
import com.springbootproject.product.mapper.CategoryMapper;
import com.springbootproject.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService  {

//    @Autowired
    private CategoryRepository categoryRepository;

    // Create Category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){ //Client (Postman / frontend) se data aaya DTO mein
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if (optionalCategory.isPresent()) {
           throw new CategoryAlreadyExistsException("Category "
                   +categoryDTO.getName()+" Already Exists!!!!");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO); // DTO → Entity
        category= categoryRepository.save(category); // Save to Database
        return CategoryMapper.toCategoryDTO(category); // Entity → DTO
    }


    // Get All Category
    public List<CategoryDTO> getAllCategories(){
       return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    // Get Category By id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found!!!!!"));
        return CategoryMapper.toCategoryDTO(category);
    }

    // Delete Category

    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "YOUR CATEGORY "+ id +" HAS BEEN SUCCESSFULLY DELETED";
    }
}
