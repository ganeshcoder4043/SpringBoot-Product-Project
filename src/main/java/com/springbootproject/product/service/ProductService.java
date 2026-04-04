package com.springbootproject.product.service;

import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;
import com.springbootproject.product.mapper.ProductMapper;
import com.springbootproject.product.repository.CategoryRepository;
import com.springbootproject.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // Create Product
    public ProductDTO createProduct(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category Not Found!!!!"));

//        DTO to Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product=productRepository.save(product); // entity save in db
        return ProductMapper.toProductDTO(product);
    }

    // Get All Product
    public List<ProductDTO> getAllProducts(){
       return productRepository.findAll()
               .stream().map(ProductMapper::toProductDTO).toList();
    }

    // Get Product By Id
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found!!!!!!"));
        return ProductMapper.toProductDTO(product);
    }

    // Update Product
     public ProductDTO updateProduct(Long id, ProductDTO productDTO){
         Product product = productRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Product Not Found!!!!!!"));

         Category category = categoryRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Category Not Found!!!!!!"));

         product.setName(productDTO.getName());
         product.setDescription(productDTO.getDescription());
         product.setPrice(productDTO.getPrice());
         product.setCategory(category);

         productRepository.save(product);

         return ProductMapper.toProductDTO(product);

     }

    // Product Delete By Id
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "YOUR PRODUCT "+ id +" HAS BEEN SUCESSFULLY DELETEDED";
    }



}
