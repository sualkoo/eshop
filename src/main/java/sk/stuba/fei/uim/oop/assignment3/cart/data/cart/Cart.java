package sk.stuba.fei.uim.oop.assignment3.cart.data.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.productId.ProductId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<ProductId> shoppingList;

    private boolean payed;

    public Cart() {
        this.shoppingList = new ArrayList<>();
    }
}
