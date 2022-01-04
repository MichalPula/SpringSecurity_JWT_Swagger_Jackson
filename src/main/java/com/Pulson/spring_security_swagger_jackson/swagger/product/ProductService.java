package com.Pulson.spring_security_swagger_jackson.swagger.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    List<Product> getAllAvailable();
    String add(Product product);
    String deactivate(Long id);
    String delete(Long id);
}
