package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdIn(List<Long> productIds);
}
