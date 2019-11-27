package zone.god.finalexamv2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.finalexamv2.model.Product;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product findById(int id);
    void save(Product product);
    void delete(int id);
    Page<Product> findAllByNameContaining(String search, Pageable pageable);
}
