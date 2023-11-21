package trancongminh_20121801.lap07.pks;

import lombok.Getter;
import lombok.Setter;
import trancongminh_20121801.lap07.models.Order;
import trancongminh_20121801.lap07.models.Product;


import java.io.Serializable;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
