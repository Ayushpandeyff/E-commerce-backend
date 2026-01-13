package com.ayush.ecommerce.service;

import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.entity.Product;

public interface CartService {
CartItem addToCart(Long  productId,int quantity);

}
