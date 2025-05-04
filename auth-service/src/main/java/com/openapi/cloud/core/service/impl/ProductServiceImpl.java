package com.openapi.cloud.core.service.impl;

import com.google.common.collect.Maps;
import com.openapi.cloud.core.constants.DeleteFlag;
import com.openapi.cloud.core.exception.ResourceNotFoundException;
import com.openapi.cloud.core.mapper.ProductMapper;
import com.openapi.cloud.core.model.dto.ModelPage;
import com.openapi.cloud.core.model.dto.ProductDto;
import com.openapi.cloud.core.model.dto.request.GetAllProductRequest;
import com.openapi.cloud.core.model.dto.request.ProductPageCondition;
import com.openapi.cloud.core.model.entities.Product;
import com.openapi.cloud.core.repository.ProductRepository;
import com.openapi.cloud.core.service.ProductService;
import com.openapi.cloud.core.util.PageUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.MAPPER.toDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", productId));

        return ProductMapper.MAPPER.toDto(product);
    }

    @Override
    public List<ProductDto> getProductList() {
        // List<Product> productList = productRepository.findAllProduct();
        List<Product> productList = productRepository.findAllByDeleteFlag(DeleteFlag.ACTIVE);
        productList.stream().forEach(System.out::println);
        return ProductMapper.MAPPER.toDtoList(productList);
    }

    @Override
    public ModelPage<ProductDto> getProductPage(GetAllProductRequest request) {

        var conditionBuilder = ProductPageCondition.builder()
                .keyword(request.getKeyword())
                .sortItem(request.getSortBy());

        var pageable = PageUtil.toPageable(request.getPage(), request.getPagingSize().getCode(), Maps.newHashMap());

        var page = productRepository.findPageByCondition(conditionBuilder.build(), pageable);

        return null;
    }
}
