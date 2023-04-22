package org.example.controler;


import lombok.RequiredArgsConstructor;
import org.example.payloads.ProductRequest;
import org.example.payloads.ProductResponse;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "")
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest product) {
        productService.addProduct(product);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(path = "{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable long productId, @RequestBody ProductResponse productResponse) {
        productService.updateProduct(productId, productResponse);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}
