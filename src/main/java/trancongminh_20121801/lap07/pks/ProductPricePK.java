package trancongminh_20121801.lap07.pks;

import lombok.Getter;
import lombok.Setter;
import trancongminh_20121801.lap07.models.Product;


import java.io.Serializable;
import java.time.LocalDateTime;

@Setter @Getter
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime price_date_time;
}
