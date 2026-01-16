package com.ayush.ecommerce.controller;

import com.ayush.ecommerce.dto.CartGetResponseDTO;
import com.ayush.ecommerce.dto.CartResponseDTO;
import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping("/addToCart/{productId}/{quantity}")
    public ResponseEntity<CartResponseDTO>addToCart(@PathVariable Long productId , @PathVariable int quantity){
        CartResponseDTO cartResponseDTO=cartService.addToCart(productId,quantity);
       return new ResponseEntity<>(cartResponseDTO, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/getCart")
    public ResponseEntity<CartGetResponseDTO>getCart(){
        CartGetResponseDTO cartGetResponseDTO=cartService.getCart();
        return new ResponseEntity<>(cartGetResponseDTO,HttpStatus.OK);

    }

}
