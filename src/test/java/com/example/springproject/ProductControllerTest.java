package com.example.springproject;


import com.example.springproject.entity.Product;
import com.example.springproject.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class ProductControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate restTemplate;

    @MockBean
    ProductRepository productRepository;

    public String createFullUrl(String s){
        return "http://localhost:" + port + s;
    }

    @Test
    void getProductName(){
        Product p = new Product(10L,"name", "description", 10.0, 0.1);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(p));
        ResponseEntity<Product> productRest = restTemplate.getForEntity(createFullUrl("/product/1"), Product.class );
        System.out.println(productRest.getBody());
        assertEquals(HttpStatus.OK, productRest.getStatusCode());
        assertNotNull(productRest.getBody());
        assertEquals(1, productRest.getBody().getId());
        assertThat(productRest.getBody())
                .hasFieldOrPropertyWithValue("name", "MacBook")
                .hasFieldOrPropertyWithValue("description", "MacBook $10k +")
                .hasFieldOrPropertyWithValue("price", 11000.00);

    }

    @Test
    void addProduct(){
        Product p = new Product("RioClaro", "chegou", 10000.0, 0.2);
        when(productRepository.save(new Product())).thenReturn(p);
        ResponseEntity<Product> productRest = restTemplate.postForEntity(createFullUrl("/product"), p, Product.class);
        System.out.println(productRest.getStatusCode());
        System.out.println(productRest.getBody());
        assertEquals(HttpStatus.CREATED, productRest.getStatusCode());
        assertThat(productRest.getBody())
                .hasFieldOrPropertyWithValue("name", "RioClaro");
    }

    @Test
    void addNullProduct(){
        Product p = new Product();
        assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(createFullUrl("/product"), p, Product.class));
    }

}
