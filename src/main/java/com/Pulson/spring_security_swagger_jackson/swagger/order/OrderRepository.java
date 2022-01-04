package com.Pulson.spring_security_swagger_jackson.swagger.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
