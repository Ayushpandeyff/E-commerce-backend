package com.ayush.ecommerce.repository;

import com.ayush.ecommerce.entity.Cart;
import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long > {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
