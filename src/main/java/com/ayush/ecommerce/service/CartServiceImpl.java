package com.ayush.ecommerce.service;


import com.ayush.ecommerce.dto.CartGetResponseDTO;
import com.ayush.ecommerce.dto.CartResponseDTO;
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

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

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
    public CartResponseDTO addToCart(Long productId, int quantity){
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
            cartItemRepository.save(cartItem);
            CartResponseDTO cartResponseDTO=new CartResponseDTO();
            cartResponseDTO.setCartItemId(cartItem.getId());
            cartResponseDTO.setQuantity(cartItem.getQuantity());
            cartResponseDTO.setProductId(productId);
            cartResponseDTO.setProductName(product.getName());
            Long price=cartItem.getQuantity()*product.getPrice();
            cartResponseDTO.setItemTotalPrice(price);
            return cartResponseDTO;
    }
    public CartGetResponseDTO getCart(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));
        Cart cart=cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("cart not found"));
        CartGetResponseDTO cartGetResponseDTO=new CartGetResponseDTO();
        cartGetResponseDTO.setCartId(cart.getId());

        Long totalprice=0L;
        int totalquan=0;
        List<CartItem> cartItemList=cart.getCartItems();
       List<CartResponseDTO> cartResponseDTOs=new ArrayList<>();
        for(CartItem cartItem:cartItemList){
            CartResponseDTO cartResponseDTO=new CartResponseDTO();
            cartResponseDTO.setProductName(cartItem.getProduct().getName());
            cartResponseDTO.setProductId(cartItem.getProduct().getId());
            cartResponseDTO.setCartItemId(cartItem.getId());
            cartResponseDTO.setQuantity(cartItem.getQuantity());
            cartResponseDTO.setItemTotalPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
            cartResponseDTOs.add(cartResponseDTO);

        }
        cartGetResponseDTO.setCartItems(cartResponseDTOs);


        for(CartItem cartItem:cartItemList){
            totalquan+=cartItem.getQuantity();
            totalprice+=cartItem.getQuantity()*cartItem.getProduct().getPrice();
        }
        cartGetResponseDTO.setTotalQuantity(totalquan);
        cartGetResponseDTO.setTotalPrice(totalprice);
        return cartGetResponseDTO;



    }


}
