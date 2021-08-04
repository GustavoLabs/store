package com.example.springproject.repositories;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
