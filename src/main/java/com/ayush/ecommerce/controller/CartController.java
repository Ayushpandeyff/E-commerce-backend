package com.ayush.ecommerce.controller;

import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping("/addToCart/{productId}/{quantity}")
    public ResponseEntity<CartItem>addToCart(@PathVariable Long productId ,@PathVariable int quantity){
        CartItem cartItem =cartService.addToCart(productId,quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }
}
