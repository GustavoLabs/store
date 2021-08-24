package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.Product;
import com.example.springproject.exception.ProductNotFoundException;
import com.example.springproject.repositories.CartRepository;
import com.example.springproject.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    public Product saveProduct(@NotNull Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(@NotNull Product product, Long id) {
        Product p = findProductById(id);
        return productRepository.save(product);
    }

    public Product findProductById(@Min(1) Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(String.format("Product %s not found", id));
        }
        return product.get();
    }

    public ResponseEntity<Product> getProductById(@Min(1) Long id){
        Product p = findProductById(id);
        try {
            return new ResponseEntity<Product>(p, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> findAllProducts() {
        Iterable<Product> products= productRepository.findAll();
        if(products.iterator().hasNext()) {
            System.out.println(products);
            return new ResponseEntity(products, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    public Iterable<Product> getAllProductsWithPagination(int numPage, int quantityPage) {
        if (quantityPage >= 15)
            quantityPage = 15;
        Pageable page = PageRequest.of(numPage, quantityPage);
        return productRepository.findAll(page);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            Product p = findProductById(id);
            String deleteMessage = "Product with id " + p.getId() + " was deleted";
            productRepository.deleteById(p.getId());
            return new ResponseEntity(deleteMessage, HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
