package com.Pulson.spring_security_swagger_jackson.swagger.order;

import com.Pulson.spring_security_swagger_jackson.swagger.exception_handler.EmptyEntitiesListException;
import com.Pulson.spring_security_swagger_jackson.swagger.exception_handler.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<OrderReadDTO> getAll() {
        List<Order> allOrders = orderRepository.findAll();

        if(allOrders.isEmpty()){
            throw new EmptyEntitiesListException(Order.class);
        }

        List<OrderReadDTO> orderReadDTOS = new ArrayList<>();
        allOrders.forEach(order -> {
            OrderReadDTO orderReadDTO = new OrderReadDTO();
            orderReadDTO.setId(order.getId());
            orderReadDTO.setCreationDate(order.getCreationDate());

            Map<String, BigDecimal> productNameToPrice = new LinkedHashMap<>();
            order.getProducts().forEach(product -> {
                productNameToPrice.put(product.getName(), product.getPrice());
            });
            orderReadDTO.setProductNameToPrice(productNameToPrice);
            orderReadDTOS.add(orderReadDTO);
        });
        return orderReadDTOS;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Order.class, id));
    }

    @Override
    public String delete(Long id) {
        Order orderToDelete = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Order.class, id));
        orderRepository.delete(orderToDelete);
        return "Order deleted!";
    }
}
