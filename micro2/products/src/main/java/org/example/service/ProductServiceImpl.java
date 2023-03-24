package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.entity.Product;
import org.example.payloads.ProductRequest;
import org.example.exception.ProductServiceException;
import org.example.payloads.ProductResponse;
import org.example.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> list = productRepository.findAll().stream().map(item -> modelMapper.map(item, ProductResponse.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        log.info("call add product method");
        Product product = modelMapper.map(productRequest, Product.class);
        if(!productRepository.findAll().contains(product)) {
            productRepository.save(product);
        } else {
            throw new ProductServiceException("product exist!", 400);
        }
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("calling product find by id!!!");
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return modelMapper.map(product, ProductResponse.class);
        }
        throw new ProductServiceException("Product not found!", 404);
    }

    @Override
    public ProductResponse updateProduct(long productId, ProductResponse productResponse) {
        Optional<Product> foundProduct = productRepository.findById(productId);
        if(foundProduct.isPresent()) {
            log.info("after found product!!");
            foundProduct.get().setTitle(productResponse.getTitle());
            foundProduct.get().setQuantity(productResponse.getQuantity());
            foundProduct.get().setPrice(productResponse.getPrice());
            productRepository.save(foundProduct.get());
            return productResponse;
        }
        else throw new ProductServiceException("Not found product!", 400);
    }

    @Override
    public ProductResponse deleteProduct(long productId) {
        Optional<Product> found = productRepository.findById(productId);
        if(found.isPresent()) {
            productRepository.delete(found.get());
            return modelMapper.map(found.get(), ProductResponse.class);
        }
        throw new ProductServiceException("product not found!", 400);
    }


    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("calling reduce quantity product inventory");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException("Product not found", 404));
        if(product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
        } else {
            throw new ProductServiceException(product.getTitle() + "Does not have enough quantity left", 400);
        }
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }

}
