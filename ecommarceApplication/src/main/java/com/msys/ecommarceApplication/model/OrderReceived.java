package com.msys.ecommarceApplication.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceived {
    Integer orderId;
    String emailAddress;
    Long mobileNumber;
    String address;
    String productId;
    String productName;
    Integer productQuantity;
    Long productPrice;
    Date orderDate;
    String orderStatus;
}
