package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.cart.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.data.productId.IProductIdRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.ProductIdRequest;
import sk.stuba.fei.uim.oop.assignment3.cart.data.productId.ProductId;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;

import java.util.List;
@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductIdRepository productIdRepository;
    @Override
    public Cart create() {
        return this.repository.save(new Cart());
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cart getById(Long id) throws NotFoundException {
        Cart cart = this.repository.findCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Cart cart = this.getById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        this.repository.delete(cart);
    }

    @Override
    public Cart addToCart(Long id, ProductIdRequest body) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);
        Product product = this.productService.getById(body.getProductId());

        if (cart == null || product == null) {
            throw new NotFoundException();
        }
        if (product.getAmount() < body.getAmount()) {
            throw new IllegalOperationException();
        }
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        boolean flag = false;
        for (ProductId response : cart.getShoppingList()) {
            if (response.getProductId().equals(body.getProductId())) {
                response.setAmount(response.getAmount() + body.getAmount());
                flag = true;
            }
        }
        if (!flag) {
            ProductId response = new ProductId(product, body.getAmount());
            this.productIdRepository.save(response);
            cart.getShoppingList().add(response);
        }
        this.productService.addAmount(body.getProductId(), (body.getAmount() * -1));
        return this.repository.save(cart);
    }

    @Override
    public String payForCart(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        double price = 0;
        Product product;
        for (ProductId response : cart.getShoppingList()) {
            product = this.productService.getById(response.getProductId());
            price = price + (product.getPrice() * response.getAmount());
        }
        cart.setPayed(true);
        this.repository.save(cart);
        return Double.toString(price);
    }
}
