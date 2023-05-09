package sk.stuba.fei.uim.oop.assignment3.cart.data.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
    Cart findCartById(Long id);
}
