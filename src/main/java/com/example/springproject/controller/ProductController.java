package com.example.springproject.controller;

import com.example.springproject.entity.Product;
import com.example.springproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        log.info("Request to create  product: {}", product);
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Long id){
        log.info("Request to update  product: {}", product);
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        log.info("Request to delete  product: {}", id);
        return productService.deleteProduct(id);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        log.info("Request to get product: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping
    public ResponseEntity<Object> findAllProducts() {
        log.info("Request to retrieve all products");
        return productService.findAllProducts();
    }

    @GetMapping(path = "/page")
    public Iterable<Product> getAllProductsWithPagination(@RequestParam int numPage, @RequestParam int quantityPage) {
        log.info("Request to retrieve products with pagination num page:{}  quantityPage:{}", numPage, quantityPage);
        return productService.getAllProductsWithPagination(numPage, quantityPage);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> map) {
        return productService.updateProduct(id, map);
    }







}
