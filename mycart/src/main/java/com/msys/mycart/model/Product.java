package com.msys.mycart.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
     @Id
     @GeneratedValue
     Long id;
     String name;
     String color;
     Integer price;
     String description;
     Integer stock;
     @Lob
     @Column(name="image",columnDefinition="mediumblob")
     byte[] image;
}
