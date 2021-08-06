package com.example.springproject.repositories;

import com.example.springproject.entity.CartProducts;
import com.example.springproject.entity.CartProductsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartProductsRepository extends CrudRepository<CartProducts, CartProductsKey> {
}
