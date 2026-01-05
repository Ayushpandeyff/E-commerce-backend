package com.ayush.ecommerce.service;

import com.ayush.ecommerce.dto.ProductCreateDTO;
import com.ayush.ecommerce.entity.Product;
import com.ayush.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    public Product createProduct(Product product){
        product.setActive(true);
     return productRepository.save(product);
    }
    public List<Product>getAllProduct(){

       return productRepository.findAll();
    }
    public Product getProductById(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        return product;

    }
    public void deleteProduct(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
    public Product updateProduct(Long id, ProductCreateDTO product){
        Product product1=productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setStock(product.getStock());
        Product product2=productRepository.save(product1);
        return product2;

    }

}
