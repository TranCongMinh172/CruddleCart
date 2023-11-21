package trancongminh_20121801.lap07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trancongminh_20121801.lap07.models.ProductPrice;
import trancongminh_20121801.lap07.pks.ProductPricePK;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {
}