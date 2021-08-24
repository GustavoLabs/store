package com.example.springproject.model.response;

import com.example.springproject.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantityDTO {
    private Product product;
    private int quantity;
}
