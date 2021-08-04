package com.example.springproject.repositories;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
