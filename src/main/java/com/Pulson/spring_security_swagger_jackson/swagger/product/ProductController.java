package com.Pulson.spring_security_swagger_jackson.swagger.product;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Product controller", description = "Controller for entity Product")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }


    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllAvailable() {
        return ResponseEntity.ok(productService.getAllAvailable());
    }


    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Product product) {
        return ResponseEntity.ok(productService.add(product));
    }


    @PatchMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deactivate(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.deactivate(productId));
    }


    @DeleteMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.delete(productId));
    }
}
