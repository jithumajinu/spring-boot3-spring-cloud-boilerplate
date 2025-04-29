package com.openapi.cloud.core.mapper;

import com.openapi.cloud.core.model.dto.ProductDto;
import com.openapi.cloud.core.model.dto.request.ProductRequest;
import com.openapi.cloud.core.model.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    ProductDto toDto(ProductRequest productRequest);

    Product toEntity(ProductDto productDto);

    Product toEntity(ProductRequest productRequest);

    List<ProductDto> toDtoList(List<Product> products);

    List<Product> toEntityList(List<ProductDto> productDtos);


//    If your field names don’t match or need transformation:
//    @Mapping(source = "name", target = "productName")
//    @Mapping(source = "price", target = "productPrice")
//    ProductDto mapToProductDto(Product product);


//    @Mapping(source = "productName", target = "name")
//    @Mapping(source = "productPrice", target = "price")
//    Product mapToProduct(ProductDto productDto);


    // Add Custom Field Formatting (e.g., format price or date)

//    @Mapping(target = "formattedPrice", expression = "java(formatPrice(product.getPrice()))")
//    ProductDto mapToProductDto(Product product);
//
//    Product mapToProduct(ProductDto productDto);
//
//    default String formatPrice(BigDecimal price) {
//        return "$" + price.setScale(2, RoundingMode.HALF_UP).toString();
//    }


//    @AfterMapping
//    default void enrichProductDto(Product product, @MappingTarget ProductDto dto) {
//        dto.setDisplayName(product.getName().toUpperCase() + " (ID: " + product.getId() + ")");
//    }


}
