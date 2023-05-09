package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.productId.ProductId;

import java.util.List;

@Getter
@Setter
public class CartResponse {
    private long id;
    private List<ProductId> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.payed = cart.isPayed();
        this.shoppingList = cart.getShoppingList();
    }
}
