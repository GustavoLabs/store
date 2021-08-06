package com.example.springproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_products")
public class CartProducts {

    @EmbeddedId
    private CartProductsKey cartProductsKeyId;

    @Column(name = "quantity")
    @Min(1)
    private int quantity;

}
