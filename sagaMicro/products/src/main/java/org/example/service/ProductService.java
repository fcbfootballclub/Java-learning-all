package org.example.service;

import org.example.dto.OrderResponseDto;
import org.example.entity.Product;
import org.example.payloads.ProductRequest;
import org.example.payloads.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();

    void addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    ProductResponse updateProduct(long productId, ProductResponse productResponse);

    ProductResponse deleteProduct(long productId);

    boolean reduceQuantity(long productId, long quantity);

}
