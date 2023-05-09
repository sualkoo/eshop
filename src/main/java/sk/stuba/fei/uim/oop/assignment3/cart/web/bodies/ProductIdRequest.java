package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductIdRequest {
    private Long productId;
    private Long amount;
}
