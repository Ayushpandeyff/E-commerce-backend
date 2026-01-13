package com.ayush.ecommerce.repository;


import com.ayush.ecommerce.entity.Cart;
import com.ayush.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUser(User user);
}
