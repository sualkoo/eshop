package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long amount;

    private String unit;

    private double price;

    public Product(ProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.amount = request.getAmount();
        this.unit = request.getUnit();
        this.price = request.getPrice();
    }
}
