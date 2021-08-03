package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @Column(name = "total_price")
    @Min(0)
    private Double totalPrice;


    public Order() {
        this(new Date());
    }

    public Order(Date date) {
        this.date = date;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name="order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    public List<Product> getProducts(){
        if (products == null){
            products = new ArrayList<>();
        }
        return products;
    }
}
