package com.openapi.cloud.core.service;

import com.openapi.cloud.core.model.dto.ProductDto;

public interface ProductService {

    ProductDto createProduct (ProductDto product);
    ProductDto getProductById (Long productId);

}
