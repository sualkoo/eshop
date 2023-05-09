package sk.stuba.fei.uim.oop.assignment3.cart.data.productId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class ProductId {

    @Id
    private Long productId;

    private Long amount;

    public ProductId(Product product, Long amount) {
        this.productId = product.getId();
        this.amount = amount;
    }
}
