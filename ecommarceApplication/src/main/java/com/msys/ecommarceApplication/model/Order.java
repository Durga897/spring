package com.msys.ecommarceApplication.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer orderId;
    private String productId;
    private String productName;
    private Integer productQuantity;
    private Integer productPrice;
    private Date orderDate;
}
