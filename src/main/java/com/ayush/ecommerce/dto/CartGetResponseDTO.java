package com.ayush.ecommerce.dto;

import com.ayush.ecommerce.entity.CartItem;

import java.util.List;

public class CartGetResponseDTO {
    private  Long cartId;
    private List<CartResponseDTO> cartItems;
    private  int totalQuantity;
    private Long totalPrice;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartResponseDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartResponseDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartGetResponseDTO() {
    }

    public CartGetResponseDTO(Long cartId, List<CartResponseDTO> cartItems, int totalQuantity, Long totalPrice) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
