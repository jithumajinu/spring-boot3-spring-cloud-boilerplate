package com.openapi.cloud.core.service.impl;

import com.openapi.cloud.core.exception.ResourceNotFoundException;
import com.openapi.cloud.core.mapper.ProductMapper;
import com.openapi.cloud.core.model.dto.ProductDto;
import com.openapi.cloud.core.model.entities.Product;
import com.openapi.cloud.core.repository.ProductRepository;
import com.openapi.cloud.core.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
      Product product = ProductMapper.MAPPER.mapToProduct(productDto);
      Product savedProduct = productRepository.save(product);
      return ProductMapper.MAPPER.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id",productId)
        );

        return ProductMapper.MAPPER.mapToProductDto(product);
    }
}
