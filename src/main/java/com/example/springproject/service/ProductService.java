package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.Product;
import com.example.springproject.exception.ProductNotFoundException;
import com.example.springproject.repositories.CartRepository;
import com.example.springproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    public Product saveProduct(@NotNull Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(@Min(1) Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(String.format("Product %s not found", id));
        }
        return product.get();
    }

    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getAllProductsWithPagination(int numPage, int quantityPage) {
        if (quantityPage >= 25)
            quantityPage = 25;
        Pageable page = PageRequest.of(numPage, quantityPage);
        return productRepository.findAll(page);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
