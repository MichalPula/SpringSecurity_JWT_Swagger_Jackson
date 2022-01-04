package com.Pulson.spring_security_swagger_jackson.swagger.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdIn(List<Long> ids);
}
