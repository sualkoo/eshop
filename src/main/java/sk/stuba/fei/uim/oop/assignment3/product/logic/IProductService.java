package sk.stuba.fei.uim.oop.assignment3.product.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request) throws NotFoundException;
    Product getById(Long id) throws NotFoundException;
    Product update(Long id, ProductUpdateRequest request) throws NotFoundException;
    void delete(Long id) throws NotFoundException;
    Long getAmount(Long id) throws NotFoundException;
    Long addAmount(Long id, Long amount) throws NotFoundException;
}
