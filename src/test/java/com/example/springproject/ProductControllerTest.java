package com.example.springproject;


import com.example.springproject.controller.ProductController;
import com.example.springproject.entity.Product;
import com.example.springproject.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@Slf4j
public class ProductControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductController productController;

    public String createFullUrl(String s){
        return "http://localhost:" + port + s;
    }

    @Test
    void findProduct(){
        Product p = new Product(10L,"name", "description", 10.0, 0.1);
        when(productRepository.findById(anyLong())).thenReturn(of(p));
        ResponseEntity<Product> productRest = restTemplate.getForEntity(createFullUrl("/product/1"), Product.class );
        log.info("Result: {}", productRest);
        assertEquals(HttpStatus.OK, productRest.getStatusCode());
        assertNotNull(productRest.getBody());
        assertEquals(10L, productRest.getBody().getId());
        assertThat(productRest.getBody())
                .hasFieldOrPropertyWithValue("name", "name")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("price", 10.0)
                .hasFieldOrPropertyWithValue("discount", 0.1);

    }

    @Test
    void findAllProducts(){
        Product p = new Product(10L,"name", "description", 10.0, 0.0);
        Product p1 = new Product(110L,"name1", "description1", 11.0, 0.1);
        Product p2 = new Product(107L,"name2", "description2", 12.0, 0.2);
        List<Product> products = Arrays.asList(p, p1, p2);
        when(productRepository.findAll()).thenReturn(products);
        List productsListRest = restTemplate.getForObject(createFullUrl("/product"), List.class );
        assertEquals(3, productsListRest.size());
        assertThat(productsListRest.get(0))
                .hasFieldOrPropertyWithValue("name", "name");
    }

    @Test
    void findAllProductsWithoutAnyProduct(){
//        when(productRepository.findAll()).thenReturn(Collections.emptyList());
//        ResponseEntity<Product[]> productRest = restTemplate.getForEntity(createFullUrl("/product"), Product[].class);
//        log.info("Result: {}", productRest);
//        assertEquals(HttpStatus.NO_CONTENT, productRest.getStatusCode());
//        assertEquals(null, productRest.getBody());
    }

    @Test
    void addProduct(){
        Product p = new Product(10L,"Notebook", "MacBook", 10000.0, 0.2);
        when(productRepository.save(any())).thenReturn(p);
        ResponseEntity<Product> productRest = restTemplate.postForEntity(createFullUrl("/product"), p, Product.class);
        log.info("Result: {}", productRest);
        assertEquals(HttpStatus.CREATED, productRest.getStatusCode());
        assertThat(productRest.getBody())
                .hasFieldOrPropertyWithValue("name", "Notebook");
    }

    @Test
    void addNullProduct(){
        Product p = new Product();
        assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(createFullUrl("/product"), p, Product.class));
    }

    @Test
    void productsWithPagination(){
//        Iterable<Product> products = Arrays.asList(
//                new Product(1L,"fake_product1","fake_description",10.0,0.1),
//                new Product(2L,"fake_product2","fake_description",10.0,0.1),
//                new Product(3L,"fake_product3","fake_description",10.0,0.1),
//                new Product(4L,"fake_product4","fake_description",10.0,0.1)
//        );
//        when(productRepository.findAll(any(Pageable.class))).thenReturn((Page<Product>) products);
//        ResponseEntity<Product> productRest = restTemplate.getForEntity(createFullUrl("/product/page?numPage=1&quantityPage=5"), Product.class);
//        log.info("Result: {}", productRest);
    }

    @Test
    void deleteProductNotExists(){
        Product product = new Product(1L,"fake_product1","fake_description",10.0,0.1);
        when(productRepository.findById(anyLong())).thenReturn(empty());
        assertThrows(HttpClientErrorException.class,
                () ->  restTemplate
                    .exchange(createFullUrl("/product/1"),
                            HttpMethod.DELETE,
                            null,
                            String.class));
    }

    @Test
    void deleteProduct(){
        Product product = new Product(1L,"fake_product1","fake_description",10.0,0.1);
        when(productRepository.findById(anyLong())).thenReturn(of(product));
        doNothing().when(productRepository).deleteById(anyLong());
        ResponseEntity<String> productRest =
                restTemplate
                        .exchange(createFullUrl("/product/1"),
                                HttpMethod.DELETE,
                                null,
                                String.class);
        assertEquals(HttpStatus.OK, productRest.getStatusCode());
        System.out.println(productRest.getBody());
        log.info("Result: {}", productRest);
        assertTrue(productRest.getBody().contains("was deleted"));
    }

    @Test
    void updateProduct(){
//        Product product = new Product(1L,"fake_product1","fake_description",10.0,0.1);
//        Product product2 = new Product(1L,"fake_product5","fake_description2",90.0,0.1);
//        String json = "{\"name:\"fake_product5\", \"description\":\"fake_description2\", \"price\":90.0}";
//
//        when(productRepository.findById(anyLong())).thenReturn(of(product));
//        when(productRepository.save(any())).thenReturn(product2);
//
//        Product response = restTemplate.patchForObject(createFullUrl("/product/100"), json, Product.class);
//        System.out.println(response);

    }


}
