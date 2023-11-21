package trancongminh_20121801.lap07.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import trancongminh_20121801.lap07.enums.ProductStatus;
import trancongminh_20121801.lap07.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByStatusNot(ProductStatus status, Pageable pageable);
}