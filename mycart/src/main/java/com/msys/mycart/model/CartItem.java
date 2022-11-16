package com.msys.mycart.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {

	private static final long serialVersionUID = -2455760938054036364L;

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
