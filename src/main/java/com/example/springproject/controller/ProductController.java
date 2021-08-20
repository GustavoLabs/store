package com.example.springproject.controller;

import com.example.springproject.entity.Product;
import com.example.springproject.exception.ProductNotFoundException;
import com.example.springproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    //@PostMapping
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @GetMapping
    public Iterable<Product> findAllProducts() {
        return productService.findAllProducts();
    }


    @GetMapping(path = "/page/{numPage}/{quantityPage}")
    public Iterable<Product> getAllProductsWithPagination(@PathVariable int numPage, @PathVariable int quantityPage) {
        return productService.getAllProductsWithPagination(numPage, quantityPage);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findProductById(@PathVariable Long id) {

        try {
            return new ResponseEntity(productService.findProductById(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

//    @GetMapping("/name/{id}")
//    public String returnProductName(@PathVariable Long id){
//        return findProductById(id).getName();
//    }

}
