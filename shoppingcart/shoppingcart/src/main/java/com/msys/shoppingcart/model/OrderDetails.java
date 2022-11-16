package com.msys.shoppingcart.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "product_amount")
    private double productAmount;

    @Column(name = "order_status")
    private String orderStatus;

}