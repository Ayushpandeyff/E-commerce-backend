package com.ayush.ecommerce.service;


import com.ayush.ecommerce.entity.Cart;
import com.ayush.ecommerce.entity.CartItem;
import com.ayush.ecommerce.entity.Product;
import com.ayush.ecommerce.entity.User;
import com.ayush.ecommerce.repository.CartItemRepository;
import com.ayush.ecommerce.repository.CartRepository;
import com.ayush.ecommerce.repository.ProductRepository;
import com.ayush.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public CartItem addToCart(Long productId, int quantity){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));


//
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));
        if(product.getStock()<quantity){
            throw new RuntimeException("product is less");
        }
            Cart cart=cartRepository.findByUser(user).orElseGet(()-> cartRepository.save(new Cart(user)));
            CartItem cartItem=cartItemRepository.findByCartAndProduct(cart,product).orElse(null);
            if(cartItem!=null){
                cartItem.setQuantity(cartItem.getQuantity()+quantity);

            }else{
                  cartItem=new CartItem(cart,product,quantity);

            }
            return cartItemRepository.save(cartItem);

//
//

    }


}
