package com.msys.ecommarceApplication.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
     String id;
     String name;
     String color;
     Integer price;
     String description;
     Integer stock;

}
