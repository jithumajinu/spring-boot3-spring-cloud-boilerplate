package com.openapi.cloud.core.service;

import com.openapi.cloud.core.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto product);

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductList();
}
