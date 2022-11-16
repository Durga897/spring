package com.msys.shoppingcart.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int quantity;

	private double price;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cartId")
	private Cart cart;

}