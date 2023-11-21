package trancongminh_20121801.lap07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trancongminh_20121801.lap07.models.OrderDetail;
import trancongminh_20121801.lap07.pks.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}