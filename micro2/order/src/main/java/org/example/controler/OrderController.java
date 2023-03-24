package org.example.controler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payload.ProductRequest;
import org.example.payload.ProductResponse;
import org.example.service.OrderService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;


@RestController
@CrossOrigin
@RequestMapping(path = "order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final RestTemplate restTemplate;

    private final String productService = "http://product-service/product";

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(productService + "/" + id, HttpMethod.GET, entity, String.class);
        log.info("get product by id");
        return response;
    }

    @GetMapping(path = "/product")
    public ResponseEntity<?> getAllProduct() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(productService, HttpMethod.GET, entity, String.class);
        log.info("this is test log");
        return exchange;
    }

    @PostMapping(path = "/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productResponse) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductRequest> productRequestHttpEntity = new HttpEntity<>(productResponse, httpHeaders);
        ResponseEntity<ProductRequest> response = restTemplate.exchange(productService, HttpMethod.POST, productRequestHttpEntity, ProductRequest.class);
        log.info("post product");
        return response;
    }

    @PutMapping(path = "product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(value = "id") long productId, @RequestBody ProductResponse productResponse) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductResponse> productResponseHttpEntity = new HttpEntity<>(productResponse, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(productService + "/" + productId, HttpMethod.PUT, productResponseHttpEntity, String.class);
        log.info("update product");
        return responseEntity;
    }

    @DeleteMapping(path = "product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(productService + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        log.info("dao delete!");
        return response;
    }
}
