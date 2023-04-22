package org.example.repository;

import org.example.entity.Product;
import org.example.payloads.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    ProductResponse getProductByProductId(long productId);
}
