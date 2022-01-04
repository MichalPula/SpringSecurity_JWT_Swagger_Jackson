package com.Pulson.spring_security_swagger_jackson.swagger.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderReadDTO> getAll();
    Order getById(Long id);
    String delete(Long id);
}
