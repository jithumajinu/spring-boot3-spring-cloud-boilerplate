package com.openapi.cloud.core.controller;

import com.openapi.cloud.core.annotations.OpenApiOperations;
import com.openapi.cloud.core.constants.ApiErrorCode;
import com.openapi.cloud.core.constants.NotificationType;
import com.openapi.cloud.core.exception.ProductNotFoundException;
import com.openapi.cloud.core.mapper.ProductMapper;
import com.openapi.cloud.core.model.ValidationGroups;
import com.openapi.cloud.core.model.dto.ApiResponse;
import com.openapi.cloud.core.model.dto.ApiResponse.ApiError;
import com.openapi.cloud.core.model.dto.OpenApiRequest;
import com.openapi.cloud.core.model.dto.OpenApiResponse;
import com.openapi.cloud.core.model.dto.ProductDto;
import com.openapi.cloud.core.model.dto.request.ProductRequest;
import com.openapi.cloud.core.service.ProductService;
import com.openapi.cloud.core.service.UserNotificationService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RefreshScope
@RestController
@RequestMapping("/openApis")
public class OpenApiController extends AbstractCoreUtilController {

    @Autowired
    private ProductService productService;

    private final UserNotificationService notificationService;

    @Autowired
    public OpenApiController(UserNotificationService notificationService) {
        this.notificationService = notificationService;
    }


    /**
     * GET
     */
    @OpenApiOperations.OpenApiListOperation(tags = "Product", summary = "", description = "Product list", operationId = "ProductList")
    @GetMapping("/product")
    public ApiResponse<List<ProductDto>> productList() {

        var responseBuilder = ApiResponse.<List<ProductDto>>builder().flag(true);
        List<ProductDto> ProductList = productService.getProductList();
        responseBuilder.data(ProductList);
        return responseBuilder.build();
    }

    /**
     * POST /product
     *
     * @param productRequest (required)
     * @return successful operation (status code 201)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "createProduct", description = "createProduct", operationId = "createProduct")
    @PostMapping("/product")
    public ApiResponse<ProductDto> createProduct(@Validated(ValidationGroups.Create.class) @RequestBody(required = true) ProductRequest productRequest,
                                                 BindingResult bindingResult, HttpServletResponse httpServletResponse) {

        var responseBuilder = ApiResponse.<ProductDto>builder().flag(true);
        if (bindingResult.hasErrors()) {
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return responseBuilder
                    .error(ApiError.builder()
                            .errorCode(ApiErrorCode.INPUT_ERROR)
                            .errors(formatInputErrors(bindingResult))
                            .build())
                    .build();
        }
        ProductDto newProduct = productService.createProduct(ProductMapper.MAPPER.toDto(productRequest));
        responseBuilder.data(newProduct);
        return responseBuilder.build();
    }

    /**
     * PUT /{productId}
     *
     * @param productId (required)
     * @param product   (required)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "Update an existing Product", description = "Update an existing Product", operationId = "updateProductIdById")
    @PutMapping("product/{productId}")
    public ResponseEntity<String> updateProductById(@PathVariable("productId") Long productId,
                                                    @Validated(ValidationGroups.Update.class) @RequestBody(required = true) ProductRequest product) {
        return ResponseEntity.ok("Resource updated successfully");
    }

    /**
     * GET /{productId}
     *
     * @param productId (required)
     * @return successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "productId", description = "productId", operationId = "productId")
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDto> productById(@PathVariable("productId") Long productId) {

        if (productId == 0) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        ProductDto productDto = productService.getProductById(productId);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    /**
     * DELETE /{productId}
     *
     * @param productId to delete (required)
     * @return Successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "deleteProduct", description = "deleteProduct", operationId = "deleteProduct")
    @DeleteMapping("products/{productId}")
    public ResponseEntity<String> deleteProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok("Resource deleted successfully");
    }


    /**
     * POST sendNotification
     *
     * @param to      (required)
     * @param subject (required)
     * @param message (required)
     * @param type    (required)
     * @return successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Notification", summary = "Notification", description = "Notification", operationId = "Notification")
    @PostMapping("/send")
    public ResponseEntity<OpenApiResponse> sendNotification(@RequestParam String to, @RequestParam String subject,
                                                            @RequestParam String message, @RequestParam NotificationType type) {
        if (type == NotificationType.EMAIL) {
            throw new ProductNotFoundException("email type exception");
        }
        notificationService.notifyUser(to, subject, message, type);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
