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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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
        productList.forEach(System.out::println);
        return ProductMapper.MAPPER.toDtoList(productList);
    }


    @Override
    public ModelPage<ProductDto> getProductPage(GetAllProductRequest request) {
        // Build search conditions
        ProductPageCondition condition = buildSearchCondition(request);

        // Create pageable object
        Pageable pageable = createPageable(request);

        // Fetch and transform data
        Page<Product> productPage = productRepository.findPageByCondition(condition, pageable);

        // Map to DTOs and build response
        return createModelPage(productPage);
    }

    private ProductPageCondition buildSearchCondition(GetAllProductRequest request) {
        return ProductPageCondition.builder()
                .keyword(request.getKeyword())
                .sortItem(request.getSortBy())
                .build();
    }

    private Pageable createPageable(GetAllProductRequest request) {
        return PageUtil.toPageable(
                request.getPage(),
                request.getPagingSize().getCode(),
                Collections.emptyMap()
        );
    }

    private ModelPage<ProductDto> createModelPage(Page<Product> page) {
        List<ProductDto> productList = page.hasContent()
                ? ProductMapper.MAPPER.toDtoList(page.getContent())
                : Collections.emptyList();

        return ModelPage.<ProductDto>builder()
                .content(productList)
                .next(page.hasNext())
                .previous(page.hasPrevious())
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalCount(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

//    @Override
//    public ModelPage<ProductDto> getProductPage(GetAllProductRequest request) {
//
//        var conditionBuilder = ProductPageCondition.builder()
//                .keyword(request.getKeyword())
//                .sortItem(request.getSortBy()).build();
//
//        Pageable pageable = PageUtil.toPageable(request.getPage(), request.getPagingSize().getCode(),
//                Maps.newHashMap());
//
//        Page<Product> page = productRepository.findPageByCondition(conditionBuilder, pageable);
//
//        List<ProductDto> productList = new ArrayList<>(); // Lists.newArrayList();
//
//        if (page.hasContent()) {
//            productList = ProductMapper.MAPPER.toDtoList(page.getContent());
//        }
//
//        return ModelPage.<ProductDto>builder()
//                .content(productList) // responseList
//                .next(page.hasNext())
//                .previous(page.hasPrevious())
//                .pageNumber(page.getNumber() + 1)
//                .pageSize(page.getSize())
//                .totalCount(page.getTotalElements())
//                .totalPages(page.getTotalPages())
//                .build();
//    }


}
