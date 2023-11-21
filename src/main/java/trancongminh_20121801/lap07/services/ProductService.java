package trancongminh_20121801.lap07.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trancongminh_20121801.lap07.enums.ProductStatus;
import trancongminh_20121801.lap07.models.Product;
import trancongminh_20121801.lap07.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

        public Page<Product> getAll(Pageable pageable){
            return productRepository.findAll(pageable);
        }
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public Product insert(Product product){
            product.setStatus(ProductStatus.ACTIVE);
        return  productRepository.save(product);
    }
    public Product update(Long id, Product product){
        return  productRepository.save(product);
    }
    public Page<Product> getAllProductNotTerminated(Pageable pageable) {
        return productRepository.findByStatusNot(ProductStatus.TERMINATED,pageable);
    }
    public void deleteProduct(Long id){
        Optional<Product>  productOptional = productRepository.findById(id);
        productOptional.ifPresent(product -> {
            product.setStatus(ProductStatus.TERMINATED);
            productRepository.save(product);
        });
    }

}
