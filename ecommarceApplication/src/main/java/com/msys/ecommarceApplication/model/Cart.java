package com.msys.ecommarceApplication.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    Integer id;
    String userId;
    Date createdDate;
}


