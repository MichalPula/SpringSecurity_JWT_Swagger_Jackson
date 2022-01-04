package com.Pulson.spring_security_swagger_jackson.swagger.product;

import com.Pulson.spring_security_swagger_jackson.swagger.exception_handler.EmptyEntitiesListException;
import com.Pulson.spring_security_swagger_jackson.swagger.exception_handler.EntityNotFoundException;
import com.Pulson.spring_security_swagger_jackson.swagger.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.isEmpty()){
            throw new EmptyEntitiesListException(Order.class);
        }
        return allProducts;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }

    @Override
    public List<Product> getAllAvailable() {
        return null;
    }

    @Override
    public String add(Product product) {
        productRepository.save(product);
        return product.getName() + " added!";
    }

    @Override
    public String deactivate(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Product.class, id));
        product.setAvailable(false);
        productRepository.save(product);
        return "Product deactivated!";
    }

    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Product deleted!";
    }
}
