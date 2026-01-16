package com.ayush.ecommerce.service;

import com.ayush.ecommerce.dto.CartGetResponseDTO;
import com.ayush.ecommerce.dto.CartResponseDTO;
import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.entity.Product;

public interface CartService {
CartResponseDTO addToCart(Long  productId, int quantity);
CartGetResponseDTO getCart();

}
