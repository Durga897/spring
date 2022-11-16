package com.msys.ecommarceApplication.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    Integer id;
    Integer cartId;
    String productId;
    String productName;
    Integer productQuantity;
    Integer productPrice;
}
