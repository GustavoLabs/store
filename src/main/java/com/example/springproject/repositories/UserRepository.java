package com.example.springproject.repositories;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
