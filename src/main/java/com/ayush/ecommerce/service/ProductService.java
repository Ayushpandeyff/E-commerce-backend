package com.ayush.ecommerce.service;

import com.ayush.ecommerce.dto.ProductCreateDTO;
import com.ayush.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
 Product createProduct(Product product);
 List<Product>getAllProduct();
 Product getProductById(Long id);
 void deleteProduct(Long id);
 Product updateProduct(Long id, ProductCreateDTO product);

}
