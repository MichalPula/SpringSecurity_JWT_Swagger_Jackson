package com.Pulson.spring_security_swagger_jackson.swagger.order;

import com.Pulson.spring_security_swagger_jackson.swagger.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders", schema = "swagger")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_products", schema = "swagger",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    private LocalDateTime creationDate;


    public Order(String customerName, List<Product> products, LocalDateTime creationDate) {
        this.customerName = customerName;
        this.products = products;
        this.creationDate = creationDate;
    }
}
