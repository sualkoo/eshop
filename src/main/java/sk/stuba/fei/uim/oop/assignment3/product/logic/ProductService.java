package sk.stuba.fei.uim.oop.assignment3.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        return this.repository.save(new Product(request));
    }

    @Override
    public Product getById(Long id) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product == null){
            throw new NotFoundException();
        }
        return product;
    }

    @Override
    public Product update(Long id, ProductUpdateRequest request) throws NotFoundException {
        Product product = this.getById(id);
        if (product == null){
            throw new NotFoundException();
        }
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        return this.repository.save(product);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Product product = this.getById(id);
        if (product == null){
            throw new NotFoundException();
        }
        this.repository.delete(product);
    }

    @Override
    public Long getAmount(Long id) throws NotFoundException {
        Product product = this.getById(id);
        if (product == null){
            throw new NotFoundException();
        }
        return product.getAmount();
    }

    @Override
    public Long addAmount(Long id, Long amount) throws NotFoundException {
        Product product = this.getById(id);
        if (product == null){
            throw new NotFoundException();
        }
        product.setAmount(product.getAmount() + amount);
        this.repository.save(product);
        return product.getAmount();
    }
}
