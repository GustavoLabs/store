package com.example.springproject.controller;

import com.example.springproject.entity.Product;
import com.example.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //	@PostMapping
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public Product saveProduct(@Valid Product product) {
        productService.saveProduct(product);
        return product;
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
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
