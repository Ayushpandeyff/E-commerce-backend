package com.ayush.ecommerce.controller;

import com.ayush.ecommerce.dto.ProductCreateDTO;
import com.ayush.ecommerce.entity.Product;
import com.ayush.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @PostMapping("/createProduct")
    public ResponseEntity<Product>createProduct(@Valid @RequestBody ProductCreateDTO dto){
        Product product=new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
         Product saved =productService.createProduct(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/Products")
    public ResponseEntity<List<Product>>getAll(){
        List<Product> a = productService.getAllProduct();
        return new ResponseEntity<>(a,HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product>getProductById(@PathVariable Long productId){
Product product= productService.getProductById(productId);
return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
@DeleteMapping("/{productId}")
    public ResponseEntity<Void>deleteProduct(@PathVariable  Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
}
@PreAuthorize("hasAnyRole('ADMIN','SUPPLIER')")
@PutMapping("/{productId}")
    public ResponseEntity<Product>updateProduct(@PathVariable Long  productId,@Valid @RequestBody ProductCreateDTO productCreateDTO ){
        Product product=productService.updateProduct(productId,productCreateDTO);
        return new ResponseEntity<>(product,HttpStatus.OK);
}

}
