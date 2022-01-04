package com.Pulson.spring_security_swagger_jackson.swagger;

import com.Pulson.spring_security_swagger_jackson.swagger.order.Order;
import com.Pulson.spring_security_swagger_jackson.swagger.order.OrderRepository;
import com.Pulson.spring_security_swagger_jackson.swagger.product.Product;
import com.Pulson.spring_security_swagger_jackson.swagger.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SwaggerInitializer {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SwaggerInitializer(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("RTX 3060", new BigDecimal("3500.00"), false),
                new Product("i5 12600KF", new BigDecimal("1450.00"), true),
                new Product("MSI PRO Z690", new BigDecimal("900.00"), true),
                new Product("Corsair Vengeance RGB PRO 32GB", new BigDecimal("1700.00"), true)
        ));
        productRepository.saveAll(products);

        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("Joe", productRepository.findAllByIdIn(Arrays.asList(1L, 2L)), LocalDateTime.now()),
                new Order("Joe", productRepository.findAllByIdIn(Arrays.asList(3L, 4L)), LocalDateTime.now()),
                new Order("Joe", productRepository.findAllByIdIn(Arrays.asList(1L, 4L)), LocalDateTime.now())
        ));
        orderRepository.saveAll(orders);
    }
}
