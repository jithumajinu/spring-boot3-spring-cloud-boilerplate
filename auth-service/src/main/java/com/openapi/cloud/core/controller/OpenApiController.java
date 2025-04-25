package com.openapi.cloud.core.controller;

import com.openapi.cloud.core.annotations.OpenApiOperations;
import com.openapi.cloud.core.constants.NotificationType;
import com.openapi.cloud.core.exception.ProductNotFoundException;
import com.openapi.cloud.core.model.dto.ApiResponse;
import com.openapi.cloud.core.model.dto.ApiResponse.ApiError;
import com.openapi.cloud.core.model.dto.OpenApiRequest;

import com.openapi.cloud.core.model.dto.OpenApiResponse;
import com.openapi.cloud.core.model.dto.ProductDto;
import com.openapi.cloud.core.service.ProductService;
import com.openapi.cloud.core.service.UserNotificationService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @OpenApiOperations.OpenApiListOperation(tags = "Customer", summary = "", description = "Customer description", operationId = "customCustomerList")
    @GetMapping("/")
    public ResponseEntity<List<OpenApiResponse>> customerList() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET /{customerId}
     *
     * @param customerId (required)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Customer", summary = "", description = "Get customer by ID", operationId = "CustomerByID")
    @GetMapping("/{customerId}")
    public ResponseEntity<OpenApiResponse> customerById(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT /{customerId}
     *
     * @param customerId (required)
     * @param customer   (required)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Customer", summary = "Update an existing customer", description = "Update an existing customer", operationId = "updateCustomerById")
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomerById(@PathVariable("customerId") Long customerId,
                                                     @RequestBody(required = true) OpenApiRequest customer) {
        return ResponseEntity.ok("Resource updated successfully");
    }

    /**
     * POST /customer
     *
     * @param customer (required)
     * @return successful operation (status code 201)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Customer", summary = "createCustomer", description = "createCustomer", operationId = "createCustomer")
    @PostMapping("/")
    public ResponseEntity<OpenApiResponse> createCustomer(@RequestBody(required = true) OpenApiRequest customer) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * DELETE /{customerId}
     *
     * @param customerId Customer id to delete (required)
     * @return Successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Customer", summary = "deleteCustomer", description = "deleteCustomer", operationId = "deleteCustomer")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok("Resource deleted successfully");
    }


    /**
     * POST /product
     *
     * @param product (required)
     * @return successful operation (status code 201)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "createProduct", description = "createProduct", operationId = "createProduct")
    @PostMapping("/createproduct")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody(required = true) ProductDto product) {
        try {
            System.out.println("create product");
            ProductDto newProduct = productService.createProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("create product exception e" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /{productId}
     *
     * @param productId (required)
     * @return successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Product", summary = "productId", description = "productId", operationId = "productId")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> productById(@PathVariable("productId") Long productId) {

        if (productId == 0) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        ProductDto productDto = productService.getProductById(productId);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    /*
     * 
     * 
     * Given that you have implemented @ControllerAdvice for global exception handling, having a try-catch block in the controller method is generally not considered a best practice. Here's why: [1]
       Redundant Error Handling : With @ControllerAdvice in place, you're essentially handling exceptions twice - once in the controller and once in the global handler.
       Code Clutter : Try-catch blocks in controllers make the code less readable and harder to maintain.
       Your code could be simplified to:
     */

    /**
     * GET /{productId}
     *
     * @param to      (required)
     * @param subject (required)
     * @param message (required)
     * @param type    (required)
     * @return successful operation (status code 200)
     */
    @OpenApiOperations.OpenApiObjectOperation(tags = "Notification", summary = "Notification", description = "Notification", operationId = "Notification")
    @PostMapping("/send")
    public ResponseEntity<OpenApiResponse> sendNotification(@RequestParam String to, @RequestParam String subject, @RequestParam String message, @RequestParam NotificationType type) {
        if (type == NotificationType.EMAIL) {
            throw new ProductNotFoundException("email type exception");
        }
        notificationService.notifyUser(to, subject, message, type);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @OpenApiOperations.OpenApiObjectOperation(tags = "testcreate", summary = "testcreate", description = "testcreate", operationId = "testcreate")
    @PostMapping("/test-create")
    public ApiResponse<ProductDto> testCreate(@Valid @RequestBody(required = true) ProductDto request, BindingResult bindingResult, HttpServletResponse httpServletResponse) {

        var responseBuilder = ApiResponse.<ProductDto>builder()
                .flag(true);
        if (bindingResult.hasErrors()) {
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return responseBuilder
                    .error(ApiError.builder()
//                            .code(ApiErrorCode.INPUT_ERROR)
                            .code("test error")
                            .errors(formatInputErrors(bindingResult))
                            .build())
                    .build();
        }
        System.out.println("--------createCustomer-- impl-------:" + request.toString());
        ProductDto newProduct = productService.createProduct(request);
        responseBuilder.data(newProduct);
        return responseBuilder.build();
        // return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

    }


//    public ApiResponse<OpenApiResponse> createCustomer(
//            @Validated @RequestBody OpenApiRequest request,
//            BindingResult bindingResult,
//            HttpServletResponse httpServletResponse) {
//
//        var responseBuilder = ApiResponse.<OpenApiResponse>builder()
//                .flag(true);
//
//        if (bindingResult.hasErrors()) {
//            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            return responseBuilder
//                    .error(ApiError.builder()
//                            .code(ApiErrorCode.INPUT_ERROR)
//                            .errors(formatInputErrors(bindingResult))
//                            .build())
//                    .build();
//        }
//
//
//        System.out.println("--------createCustomer-- impl-------:" + request.toString());
//
//        try {
//            System.out.println("--------createCustomer-- impl-------:" + request.toString());
//            CustomerGroupResponse response = customerService.createCustomer(request);
//            responseBuilder.data(response);
//        } catch (IllegalArgumentException e) {
//            System.out.println("--------createCustomer-- IllegalArgumentException-------:" + e.toString());
//            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            return responseBuilder.build();
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("--------cDataIntegrityViolationException -3------:" + e.toString());
//            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            return responseBuilder
//                    .error(ApiError.builder()
//                            .code(ApiErrorCode.INPUT_ERROR)
//                            .message("A customer with the same email and phone number already exists.")
//                            .build())
//                    .build();
//        }
//
//        return responseBuilder.build();
//    }


}
