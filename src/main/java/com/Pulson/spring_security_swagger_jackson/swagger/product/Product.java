package com.Pulson.spring_security_swagger_jackson.swagger.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products", schema = "swagger")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private BigDecimal price;

    private boolean isAvailable = true;

    public Product(String name, BigDecimal price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
