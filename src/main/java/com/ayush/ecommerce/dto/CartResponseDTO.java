package com.ayush.ecommerce.dto;

public class CartResponseDTO {

    private Long cartItemId;
    private Long productId;
    private String productName;
    private int quantity;
    private Long itemTotalPrice;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(Long itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public CartResponseDTO() {
    }

    public CartResponseDTO(Long cartItemId, Long productId, String productName, int quantity, Long itemTotalPrice) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.itemTotalPrice = itemTotalPrice;
    }
}
