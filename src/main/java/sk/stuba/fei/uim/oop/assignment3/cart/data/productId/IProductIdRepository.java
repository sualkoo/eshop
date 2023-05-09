package sk.stuba.fei.uim.oop.assignment3.cart.data.productId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductIdRepository extends JpaRepository<ProductId, Long> {
}
