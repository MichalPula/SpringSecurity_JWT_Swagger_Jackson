package com.Pulson.spring_security_swagger_jackson.swagger.order;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Order controller", description = "Controller for entity Order")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ApiOperation(value = "Returns all orders as OrderReadDTO List", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No orders were found!")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderReadDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }


    @ApiOperation(value = "Returns full order object by its ID", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Order with id was not found! ")
    })
    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getById(orderId));
    }


    @ApiOperation(value = "Deletes order with given id")
    @DeleteMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.delete(orderId));
    }

}
